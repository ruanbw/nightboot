package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.req.role.ChangeRoleStatusDto;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.req.role.SaveRoleDto;
import com.nightboot.domain.req.role.UpdateRoleDto;
import com.nightboot.domain.res.role.RoleInfoVo;
import com.nightboot.domain.res.role.RoleListVo;

import java.util.List;

public interface RoleService extends IService<RolePo> {

    Page<RoleListVo> findAll(RolePageDto dto);

    /**
     * 根据用户ID查询所有关联的角色
     * @param userId 用户ID
     * @return List<SysRolePo> 关联的角色列表
     */
    List<RolePo> findRolesByUserId(String userId);

    void save(SaveRoleDto dto);

    void update(UpdateRoleDto dto);

    void del(String id);

    void setRoleStatus(ChangeRoleStatusDto dto);

    RoleInfoVo queryRoleById(String roleId);

    RolePo findOne(String roleId);

    boolean exist(String roleId);

}
