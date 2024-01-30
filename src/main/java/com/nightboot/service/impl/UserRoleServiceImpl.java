package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.domain.po.RolePo;
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

    @Override
    public void save(String userId, List<String> roleIds) {
        QueryWrapper<UserRolePo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<UserRolePo> list = this.list(wrapper);
        list.forEach(item->{
            this.removeById(item.getId());
        });
        roleIds.forEach(roleId->{
            UserRolePo userRole = new UserRolePo();
            userRole.setId(IdUtils.nextId());
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            save(userRole);
        });

    }
}
