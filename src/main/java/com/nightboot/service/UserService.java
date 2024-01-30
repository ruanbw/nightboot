package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.po.UserPo;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserInfoVo;
import com.nightboot.domain.res.user.UserPageVo;

public interface UserService extends IService<UserPo> {

    /**
     * 查询用户列表
     */
    Page<UserPageVo> findAll(UserPageDto dto);

    /**
     * 根据ID查询用户
     */
    UserInfoVo findOne(String userId);

    /**
     * 根据用户名查询用户
     */
    UserPo loadUserByUsername(String username);

    /**
     * 添加用户信息
     */
    void save(SaveUserDto dto);

    /**
     * 更新用户信息
     */
    void update(UpdateUserDto dto);

    /**
     * 根据ID删除用户
     */
    void del(String userId);
}
