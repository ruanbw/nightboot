package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.po.UserRolePo;
import com.nightboot.domain.req.role.ChangeRoleStatusDto;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.req.role.SaveRoleDto;
import com.nightboot.domain.req.role.UpdateRoleDto;
import com.nightboot.domain.res.role.RoleListVo;
import com.nightboot.mapper.RoleMapper;
import com.nightboot.service.RoleService;
import com.nightboot.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Page<RoleListVo> findAll(RolePageDto dto) {
        Page<RoleListVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<RoleListVo> list = this.baseMapper.findAll(page, dto);
        page.setRecords(list);
        return page;
    }

    @Override
    public void save(SaveRoleDto dto) {
        RolePo role = query().eq("role_value", dto.getRoleValue()).one();
        if(StringUtils.isNotNull(role)){
            throw new ServiceException("角色已存在");
        }
        RolePo newRole = new RolePo();
        BeanUtils.copyProperties(dto,newRole);
        newRole.setId(IdUtils.nextId());
        newRole.setCreateBy(SecurityUtils.getUserId());
        save(newRole);
    }

    @Override
    public void update(UpdateRoleDto dto) {
        RolePo role = getById(dto.getId());
        if(StringUtils.isNull(role)){
            throw new ServiceException("角色不存在");
        }
        BeanUtils.copyProperties(dto,role);
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public void del(String id) {
        RolePo role = getById(id);
        if(StringUtils.isNull(role)){
            throw new ServiceException("角色不存在");
        }
        role.setStatus(2);
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public void setRoleStatus(ChangeRoleStatusDto dto) {
        RolePo role = getById(dto.getId());
        if(StringUtils.isNull(role)){
            throw new ServiceException("角色不存在");
        }
        role.setStatus(dto.getStatus());
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public List<RolePo> findRolesByUserId(String userId) {
        List<UserRolePo> roleIds = userRoleService.findRoleIdsByUserId(userId);
        List<RolePo> roles = new ArrayList<>();
                roleIds.forEach(item-> {
            Long roleId = item.getRoleId();
            RolePo role = query().eq("id", roleId).and(i->i.eq("status", 0)).one();
            roles.add(role);
        });
        return roles;
    }
}
