package com.atguigu.spzx.manager.service.impl.system;

import com.atguigu.spzx.manager.mapper.system.SysMenuMapper;
import com.atguigu.spzx.manager.service.system.SysMenuService;
import com.atguigu.spzx.model.dto.system.SysRoleMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.MenuTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<MenuTreeVo> getMenuTree() {
        return buildTree(sysMenuMapper.selectAllMenus());
    }

    @Override
    public boolean addMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() == null || sysMenu.getParentId() <= 0) {
            sysMenu.setParentId(0l);
        }
        int res = sysMenuMapper.insertSysMenu(sysMenu);
        return res > 0;
    }

    @Override
    public boolean modifyMenu(SysMenu sysMenu) {
        int res = sysMenuMapper.updateSysMenu(sysMenu);
        return res > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public SysMenu getById(Long id) {
        return sysMenuMapper.selectMenuById(id);
    }

    @Override
    public boolean removeById(Long id) {
        if(sysMenuMapper.getChildrenNum(id) > 0){
            return false;
        }
        return sysMenuMapper.deleteById(id) > 0;
    }

    @Override
    public Map<String, Object> getRoleMenuTree(Long roleId) {
        List<MenuTreeVo> menuTree = this.getMenuTree();
        List<Long> menusByRole = sysMenuMapper.getMenusByRoleId(roleId);
        Map<String, Object> map = new HashMap<>();
        map.put("menuTree", menuTree != null ? menuTree : new ArrayList());
        map.put("menusInRole", menusByRole != null ? menusByRole : new ArrayList());
        return map;
    }

    @Override
    public void assignRoleMenu(SysRoleMenuDto sysRoleMenuDto) {
        // 删除role下面的所有Menu
        int remRes = sysMenuMapper.deleteMenusByRoleId(sysRoleMenuDto.getRoleId());
        // 将新的菜单插入进去
        if(!sysRoleMenuDto.getMenuIdList().isEmpty()){
            int insRes = sysMenuMapper.insertSysRoleMenu(sysRoleMenuDto);
        }
    }

    /**
     * 使用递归方法建菜单
     *
     * @param sysMenuList
     * @return
     */
    private List<MenuTreeVo> buildTree(List<MenuTreeVo> sysMenuList) {
        List<MenuTreeVo> trees = new ArrayList<>();
        for (MenuTreeVo menuTree : sysMenuList) {
            if (menuTree.getParentId().longValue() == 0) {
                trees.add(findChildren(menuTree, sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private MenuTreeVo findChildren(MenuTreeVo menuTreeVo, List<MenuTreeVo> treeNodes) {
        menuTreeVo.setChildren(new ArrayList<MenuTreeVo>());
        for (MenuTreeVo mt : treeNodes) {
            if (menuTreeVo.getId().longValue() == mt.getParentId().longValue()) {
                if (menuTreeVo.getChildren() == null) {
                    menuTreeVo.setChildren(new ArrayList<>());
                }
                menuTreeVo.getChildren().add(findChildren(mt, treeNodes));
            }
        }
        return menuTreeVo;
    }
}
