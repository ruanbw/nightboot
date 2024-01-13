package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.bo.rolemenu.RoleMenuBo;
import com.nightboot.domain.po.MenuPo;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.domain.res.menu.MetaVo;
import com.nightboot.mapper.MenuMapper;
import com.nightboot.service.MenuService;
import com.nightboot.service.RoleMenuService;
import com.nightboot.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPo> implements MenuService {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RoleService roleService;

    @Override
    public List<MenuListVo> findAll(MenuPageDto dto) {
        List<MenuListVo> list = this.baseMapper.findAll(dto);
        return permissionsToTree(list);
    }

    @Override
    public List<MenuListVo> getMenuList() {
        String userId = SecurityUtils.getUserId();
        // 查询当前用户所有角色
        List<RolePo> roleList = roleService.findRolesByUserId(userId);
        List<String> roleIds = roleList.stream().map(RolePo::getId).collect(Collectors.toList());

        // 根据角色id查询菜单
        List<String> menuIds = new ArrayList<>();
        roleIds.forEach(roleId -> {
            menuIds.addAll(roleMenuService.queryRoleMenuIds(roleId));
        });

        // 去重menuIds
        Set<String> menuIdSet = new HashSet<>(menuIds);
        List<String> menuIdsList = new ArrayList<>(menuIdSet);

        // 根据menuIds查询菜单信息
        List<MenuPo> menuPoList = listByIds(menuIdsList);

        // 将menuPoList转换成MenuListVo
        List<MenuListVo> menuListVos = new ArrayList<>();

        menuPoList.forEach(menuPo -> {
            MenuListVo menuListVo = new MenuListVo();
            BeanUtils.copyProperties(menuPo, menuListVo);
            menuListVos.add(menuListVo);
        });

        // 排序menuListVos
        menuListVos.sort(Comparator.comparing(MenuListVo::getOrderNo));

        return permissionsToTree(menuListVos);
    }

    @Override
    public void save(SaveMenuDto dto) {
        MenuPo newMenu = new MenuPo();
        BeanUtils.copyProperties(dto, newMenu);
        newMenu.setId(IdUtils.nextId());
        newMenu.setCreateBy(SecurityUtils.getUserId());
        save(newMenu);
    }

    @Override
    public void update(UpdateMenuDto dto) {
        MenuPo menu = getById(dto.getId());
        if (StringUtils.isNull(menu)) {
            throw new ServiceException("菜单不存在");
        }
        BeanUtils.copyProperties(dto, menu);
        menu.setUpdateBy(SecurityUtils.getUserId());
        updateById(menu);
    }

    @Override
    public void del(String id) {
        MenuPo menu = getById(id);
        if (StringUtils.isNull(menu)) {
            throw new ServiceException("菜单不存在");
        }
        MenuPo childrenMenu = query().eq("parent_id", id).one();
        if (StringUtils.isNotNull(childrenMenu)) {
            throw new ServiceException("请先删除子级");
        }
        // 删除菜单
        this.baseMapper.deleteById(menu);
        // 删除角色关联权限信息
        roleMenuService.deletePermission(id);
    }

    /**
     * 根据权限数组组成权限树
     */
    private List<MenuListVo> permissionsToTree(List<MenuListVo> permissions) {
        List<MenuListVo> topTree = new ArrayList<>();
        Map<String, List<MenuListVo>> childMap = new HashMap<>();
        Map<String, MenuListVo> parentMap = new HashMap<>();
        for (MenuListVo m : permissions) {
            MetaVo metaVo = new MetaVo();
            metaVo.setTitle(m.getTitle());
            metaVo.setIcon(m.getIcon());
            m.setMeta(metaVo);
            parentMap.put(m.getId(), m);
            if (StringUtils.isEmpty(m.getParentId())) {
                topTree.add(m);
                List<MenuListVo> childs = childMap.get(m.getId());
                if (Objects.isNull(childs)) {
                    childs = new ArrayList<>();
                    childMap.put(m.getId(), childs);
                }
                m.setChildren(childs);
            } else {
                // 维护兄弟节点
                List<MenuListVo> brothers = childMap.get(m.getParentId());
                if (Objects.isNull(brothers)) {
                    brothers = new ArrayList<>();
                    childMap.put(m.getParentId(), brothers);
                    m.setChildren(brothers);
                }
                brothers.add(m);

                // 维护自己的子节点
                List<MenuListVo> myChilds = childMap.get(m.getId());
                if (Objects.isNull(myChilds)) {
                    myChilds = new ArrayList<>();
                    childMap.put(m.getId(), myChilds);
                }
                m.setChildren(myChilds);
            }
        }
        return topTree;
    }
}
