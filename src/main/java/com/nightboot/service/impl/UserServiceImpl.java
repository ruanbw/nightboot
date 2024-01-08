package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.enums.ResultEnum;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.UserPo;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserPageVo;
import com.nightboot.mapper.UserMapper;
import com.nightboot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

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
    public void save(SaveUserDto dto) {
        UserPo user = loadUserByUsername(dto.getUsername());
        if (StringUtils.isNotNull(user)) {
            throw new ServiceException("该账号已存在");
        }
        UserPo newUser = new UserPo();
        newUser.setId(IdUtils.nextId());
        newUser.setUsername(dto.getUsername());
        String newPwd = new BCryptPasswordEncoder().encode(dto.getPassword());
        newUser.setPassword(newPwd);
        newUser.setNickName(dto.getNickName());
        newUser.setCreateBy(SecurityUtils.getUserId());
        save(newUser);
    }

    @Override
    public void update(UpdateUserDto dto) {
        UserPo user = getById(dto.getId());
        if (StringUtils.isNull(user)) {
            throw new ServiceException("用户不存在");
        }
        BeanUtils.copyProperties(dto, user);
        user.setUpdateBy(SecurityUtils.getUserId());
        updateById(user);
    }

    @Override
    public void del(String id) {
        UserPo user = getById(id);
        if (StringUtils.isNull(user)) {
            throw new ServiceException("用户不存在");
        }
        user.setStatus(2);
        user.setUpdateBy(SecurityUtils.getUserId());
        updateById(user);
    }

    @Override
    public UserPo getUserInfo(String userId) {
        UserPo user = getById(userId);
        if (StringUtils.isNull(user)) {
            throw new ServiceException(ResultEnum.NOT_FOUND.getMsg(), ResultEnum.NOT_FOUND.getCode());
        }
        if(user.getStatus() != 0){
            throw new ServiceException("用户已冻结");
        }
        return user;
    }
}
