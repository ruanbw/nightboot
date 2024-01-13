package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.bo.rolemenu.RoleMenuBo;
import com.nightboot.domain.po.MenuPo;
import com.nightboot.domain.po.RoleMenuPo;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.mapper.MenuMapper;
import com.nightboot.mapper.RoleMenuMapper;
import com.nightboot.service.MenuService;
import com.nightboot.service.RoleMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuPo> implements RoleMenuService {

    @Override
    public void saveRolePermissions(String roleId, List<String> permissionIds) {
        List<RoleMenuPo> list = new ArrayList<>();
        for(String menuId : permissionIds){
            RoleMenuPo entity = new RoleMenuPo();
            entity.setId(IdUtils.nextId());
            entity.setMenuId(menuId);
            entity.setRoleId(roleId);
            list.add(entity);
        }
        this.baseMapper.batchSave(list);
    }


    @Override
    public List<String> queryRoleMenuIds(String rolelId) {
        List<RoleMenuPo> roleMenuList = list(new QueryWrapper<RoleMenuPo>().eq("role_id", rolelId));
        List<String> list = new ArrayList<>();
        roleMenuList.forEach(item->{
            list.add(item.getMenuId());
        });
        return list;
    }

    @Override
    public void updateRolePermissions(String roleId, List<String> permissionIds, String updateUserId) {
        // 先删除原有的关联关系，再新增
        UpdateWrapper<RoleMenuPo> update = new UpdateWrapper<>();
        update.eq("role_id",roleId);
        this.baseMapper.delete(update);
        List<RoleMenuPo> list = new ArrayList<>();
        for(String permissionId : permissionIds){
            RoleMenuPo entity = new RoleMenuPo();
            entity.setId(IdUtils.nextId());
            entity.setRoleId(roleId);
            entity.setMenuId(permissionId);
            list.add(entity);
        }
        this.baseMapper.batchSave(list);
    }

    @Override
    public void deletePermission(String permissionId) {
        UpdateWrapper<RoleMenuPo> update = new UpdateWrapper<>();
        update.eq("menu_id",permissionId);
        this.baseMapper.delete(update);
    }
}
