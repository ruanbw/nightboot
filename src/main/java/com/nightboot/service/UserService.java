package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.UserPo;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserPageVo;

public interface UserService {

    Page<UserPageVo> findAll(UserPageDto dto);

    UserPo loadUserByUsername(String username);

    UserPo getUserInfo(String userId);

    void save(SaveUserDto dto);

    void update(UpdateUserDto dto);

    void del(String id);
}
