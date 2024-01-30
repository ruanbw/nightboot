package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.CommonException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.po.UserRolePo;
import com.nightboot.domain.req.role.ChangeRoleStatusDto;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.req.role.SaveRoleDto;
import com.nightboot.domain.req.role.UpdateRoleDto;
import com.nightboot.domain.res.role.RoleInfoVo;
import com.nightboot.domain.res.role.RoleListVo;
import com.nightboot.mapper.RoleMapper;
import com.nightboot.service.RoleMenuService;
import com.nightboot.service.RoleService;
import com.nightboot.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RolePo> implements RoleService {

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuService roleMenuService;

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
        if (StringUtils.isNotNull(role)) {
            throw CommonException.fail("角色已存在");
        }
        RolePo newRole = new RolePo();
        BeanUtils.copyProperties(dto, newRole);
        newRole.setId(IdUtils.nextId());
        newRole.setCreateBy(SecurityUtils.getUserId());
        save(newRole);
        roleMenuService.saveRolePermissions(newRole.getId(), dto.getMenu());
    }

    @Override
    public void update(UpdateRoleDto dto) {
        RolePo role = getById(dto.getId());
        if (StringUtils.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        BeanUtils.copyProperties(dto, role);
        String userId = SecurityUtils.getUserId();
        role.setUpdateBy(userId);
        updateById(role);
        roleMenuService.updateRolePermissions(role.getId(), dto.getMenu(), userId);
    }

    @Override
    public void del(String id) {
        RolePo role = getById(id);
        if (StringUtils.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        role.setStatus(2);
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public void setRoleStatus(ChangeRoleStatusDto dto) {
        RolePo role = getById(dto.getId());
        if (StringUtils.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        role.setStatus(dto.getStatus());
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public List<RolePo> findRolesByUserId(String userId) {
        List<UserRolePo> roleIds = userRoleService.findRoleIdsByUserId(userId);
        List<RolePo> roles = new ArrayList<>();
        roleIds.forEach(item -> {
            String roleId = item.getRoleId();
            RolePo role = query().eq("id", roleId).and(i -> i.eq("status", 0)).one();
            roles.add(role);
        });
        return roles;
    }

    @Override
    public RoleInfoVo queryRoleById(String roleId) {
        RolePo role = getById(roleId);
        if (Objects.isNull(role)) {
            // 角色信息不存在
            throw CommonException.fail("角色信息不存在");
        }
        RoleInfoVo result = new RoleInfoVo();
        BeanUtils.copyProperties(role, result);
        // 查询角色权限信息
        List<String> menuIds = roleMenuService.queryRoleMenuIds(role.getId());
        result.setMenu(menuIds);
        return result;
    }

    @Override
    public RolePo findOne(String roleId) {
        RolePo role = getById(roleId);
        if(StringUtils.isNull(role) || role.getStatus() == 2){
            throw CommonException.fail("角色信息不存在");
        }
        return role;
    }

    @Override
    public boolean exist(String roleId) {
        return findOne(roleId) != null;
    }
}
