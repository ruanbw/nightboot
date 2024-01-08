package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.domain.po.UserRolePo;
import com.nightboot.mapper.UserRoleMapper;
import com.nightboot.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRolePo> implements UserRoleService {
    @Override
    public List<UserRolePo> findRoleIdsByUserId(String userId) {
        return query().eq("user_id", userId).list();
    }
}
