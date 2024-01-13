package com.atguigu.spzx.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.common.log.service.AsyncOptLogService;
import com.atguigu.spzx.common.log.utils.IpUtil;
import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class SysOperLogAspect {

    @Autowired
    private AsyncOptLogService asyncOptLogService;

    @Around(value = "@annotation(log)")
    public Object asyncSysOperLog(ProceedingJoinPoint joinPoint, Log log) {
        Object result = null;
        SysOperLog sysOperLog = new SysOperLog();
        try {
            generateLogBeforeMethod(sysOperLog, log, joinPoint);
            result = joinPoint.proceed();
            generateLogAfterMethod(sysOperLog, log, result, null);
        } catch (Throwable e) {
            generateLogAfterMethod(sysOperLog, log, null, e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private void generateLogAfterMethod(SysOperLog sysOperLog, Log log, Object result, Throwable e) {
        sysOperLog.setStatus(0);
        if (e != null) {
            sysOperLog.setStatus(1);
            sysOperLog.setErrorMsg(e.getMessage());
        }

        if (log.isSaveResponseData()) {
            sysOperLog.setJsonResult(JSON.toJSONString(result));
        }
        asyncOptLogService.saveSysOperLog(sysOperLog);
    }

    private void generateLogBeforeMethod(SysOperLog sysOperLog, Log log, ProceedingJoinPoint joinPoint) {
        sysOperLog.setTitle(log.title());   // 标题
        sysOperLog.setBusinessType(log.businessType().name());  // 业务类型
        sysOperLog.setOperatorType(log.operatorType().name());  // 操作人类型

        // 获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取Request中的参数
        String requestMethod = request.getMethod();
        sysOperLog.setRequestMethod(requestMethod);   // 请求方式
        sysOperLog.setOperIp(IpUtil.getIpAddress(request)); // 获取请求的IP地址
        sysOperLog.setOperUrl(request.getRequestURI());     // 获取请求路径

        sysOperLog.setOperName(AuthContextUtil.get().getName());// 获取操作人员

        String methodName = joinPoint.getSignature().getName();    // 获取方法名
        String className = joinPoint.getTarget().getClass().getName();  // 获取类名
        String finalMethodName = className + "." + methodName + "()";
        sysOperLog.setMethod(finalMethodName);  // 设置最终方法名

        // 检查是否添加请求参数
        if (requestMethod.equals(HttpMethod.POST.name()) || requestMethod.equals(HttpMethod.PUT.name())) {
            if (log.isSaveRequestData()) {
                String paramsString = buildParamsString(joinPoint.getArgs());
                sysOperLog.setOperParam(paramsString);
            }
        }
    }

    // 参数拼装
    private String buildParamsString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!StringUtils.isEmpty(o) && !isFilterObject(o)) {
                    try {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {       // 判断是否为数组类型
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);  // 如果是数组，判断其元素类型是否为MultipartFile或其子类
        } else if (Collection.class.isAssignableFrom(clazz)) { // 判断是否为Collection集合类型
            Collection collection = (Collection) o;
            for (Object value : collection) {  // 只要有一个元素的类型为MultipartFile或其子类，则认为该集合对象为过滤对象
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {  // 判断是否为Map集合类型
            Map map = (Map) o;
            for (Object value : map.entrySet()) {  // 只要有一个Value的类型是MultipartFile或其子类，则认为该映射对象为过滤对象
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }

        // 如果以上条件都不满足，最后判断对象本身是否为MultipartFile、HttpServletRequest、HttpServletResponse类的实例
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
