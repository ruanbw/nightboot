package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.enums.UserStatusEnum;
import com.nightboot.common.exception.CommonException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.po.UserPo;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.role.RoleInfoVo;
import com.nightboot.domain.res.user.UserInfoVo;
import com.nightboot.domain.res.user.UserPageVo;
import com.nightboot.mapper.UserMapper;
import com.nightboot.service.RoleService;
import com.nightboot.service.UserRoleService;
import com.nightboot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;

    @Override
    public UserPo loadUserByUsername(String username) {
        return query().eq("username", username).one();
    }

    @Override
    public Page<UserPageVo> findAll(UserPageDto dto) {
        Page<UserPageVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<UserPageVo> list = this.baseMapper.findAll(page, dto);
        page.setRecords(list);
        return page;
    }

    @Override
    public UserInfoVo findOne(String userId) {
        UserPo user = getById(userId);
        if(StringUtils.isNull(user) || Objects.equals(user.getStatus(), UserStatusEnum.delete.getStatus())){
            throw CommonException.fail("用户信息不存在");
        }
        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(user,vo);
        List<RolePo> roles = roleService.findRolesByUserId(userId);
        List<RoleInfoVo> roleInfoVos = new ArrayList<>();
        roles.forEach(role->{
            RoleInfoVo vo1 = new RoleInfoVo();
            BeanUtils.copyProperties(role,vo1);
            roleInfoVos.add(vo1);
        });
        vo.setRoles(roleInfoVos);
        return vo;
    }

    @Override
    public void save(SaveUserDto dto) {
        UserPo user = loadUserByUsername(dto.getUsername());
        if (StringUtils.isNotNull(user)) {
            throw CommonException.fail("该账号已存在");
        }
        UserPo newUser = new UserPo();
        String userId = IdUtils.nextId();
        newUser.setId(userId);
        newUser.setUsername(dto.getUsername());
        String newPwd = new BCryptPasswordEncoder().encode(dto.getPassword());
        newUser.setPassword(newPwd);
        newUser.setNickName(dto.getNickName());
        newUser.setCreateBy(SecurityUtils.getUserId());
        save(newUser);
        List<String> roleIds = dto.getRoleIds();
        roleIds.forEach(roleId->{
            roleService.exist(roleId);
        });
        userRoleService.save(userId,roleIds);
    }

    @Override
    public void update(UpdateUserDto dto) {
        UserPo user = getById(dto.getId());
        if (StringUtils.isNull(user) || Objects.equals(user.getStatus(), UserStatusEnum.delete.getStatus())) {
            throw CommonException.fail("用户不存在");
        }
        BeanUtils.copyProperties(dto, user);
        user.setUpdateBy(SecurityUtils.getUserId());
        updateById(user);
        List<String> roleIds = dto.getRoleIds();
        roleIds.forEach(roleId->{
            roleService.exist(roleId);
        });
        userRoleService.save(user.getId(),roleIds);
    }

    @Override
    public void del(String userId) {
        UserPo user = getById(userId);
        if (StringUtils.isNull(user)) {
            throw CommonException.fail("用户不存在");
        }
        user.setStatus(2);
        user.setUpdateBy(SecurityUtils.getUserId());
        updateById(user);
    }

}
