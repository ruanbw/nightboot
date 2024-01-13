package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.bo.rolemenu.RoleMenuBo;
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
import java.util.*;

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
            throw new ServiceException("角色已存在");
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
            throw new ServiceException("角色不存在");
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
            throw new ServiceException("角色不存在");
        }
        role.setStatus(2);
        role.setUpdateBy(SecurityUtils.getUserId());
        updateById(role);
    }

    @Override
    public void setRoleStatus(ChangeRoleStatusDto dto) {
        RolePo role = getById(dto.getId());
        if (StringUtils.isNull(role)) {
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
        roleIds.forEach(item -> {
            Long roleId = item.getRoleId();
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
            throw new ServiceException("角色信息不存在");
        }
        RoleInfoVo result = new RoleInfoVo();
        BeanUtils.copyProperties(role, result);
        // 查询角色权限信息
        List<String> menuIds = roleMenuService.queryRoleMenuIds(role.getId());
        result.setMenu(menuIds);
        return result;
    }
//
//    /**
//     * 根据parentId组装权限树
//     */
//    private List<PermissionTreeDto> assemblePermissionTree(List<RolePermissionsBO> permissions){
//        List<PermissionTreeDto> treeList = new ArrayList<>();
//        if(Objects.nonNull(permissions) && permissions.size() > 0){
//            Map<String,List<PermissionTreeDto>> childrenMap = new HashMap<>();
//            Map<String,PermissionTreeDto> parentMap = new HashMap<>();
//            for(RolePermissionsBO vo : permissions){
//                if(Objects.nonNull(vo.getParentId())){
//                    // 上级id不为空，则说明这是个下级节点，通过map寻找上级节点，把自己挂载进去
//                    List<PermissionTreeDto> parentChildrens = childrenMap.get(vo.getParentId());
//                    if(Objects.isNull(parentChildrens)){
//                        parentChildrens = new ArrayList<>();
//                        childrenMap.put(vo.getParentId(),parentChildrens);
//                    }
//                    // 初始化树节点上的子节点信息，并把自己挂载在树节点上
//                    PermissionTreeDto children = new PermissionTreeDto();
//                    children.setId(vo.getId());
//                    children.setParentId(vo.getParentId());
//                    children.setPermissionName(vo.getPermissionName());
//                    children.setType(vo.getType());
//                    parentChildrens.add(children);
//
//                    // 初始化树节点的父级节点信息，并将父级下的子级数组挂载上去
//                    PermissionTreeDto parent = parentMap.get(vo.getParentId());
//                    if(Objects.isNull(parent)){
//                        parent = new PermissionTreeDto();
//                        parentMap.put(vo.getParentId(),parent);
//                        // 上级节点设置下级节点数组
//                        parent.setChildrens(parentChildrens);
//                    }
//                }else{
//                    // 初始化最顶级节点
//                    PermissionTreeDto parent = new PermissionTreeDto();
//                    parent.setType(vo.getType());
//                    parent.setPermissionName(vo.getPermissionName());
//                    parent.setId(vo.getId());
//                    treeList.add(parent);
//                }
//            }
//        }
//        return treeList;
//    }
}
