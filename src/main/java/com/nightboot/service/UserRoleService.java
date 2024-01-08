package com.nightboot.service;

import com.nightboot.domain.po.UserRolePo;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据用户ID查询所有关联的角色
     * @param userId 用户ID
     * @return List<SysRolePo> 关联的角色列表
     */
    List<UserRolePo> findRoleIdsByUserId(String userId);
}
