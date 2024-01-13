package com.nightboot.service;

import com.nightboot.domain.bo.rolemenu.RoleMenuBo;
import com.nightboot.domain.po.RoleMenuPo;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuService {

    /**
     * 新增角色权限关联关系
     */
    void saveRolePermissions(String roleId, List<String> permissionIds);

    /**
     *  查询角色权限信息
     */
    List<String> queryRoleMenuIds(String rolelId);

    /**
     * 更新角色权限关联关系
     */
    void updateRolePermissions(String roleId,List<String> permissionIds,String updateUserId);

    /**
     * 删除角色所拥有的指定权限信息
     */
    void deletePermission(String permissionId);
}
