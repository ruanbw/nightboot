package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.role.ChangeRoleStatusDto;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.req.role.SaveRoleDto;
import com.nightboot.domain.req.role.UpdateRoleDto;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.res.role.RoleInfoVo;
import com.nightboot.domain.res.role.RoleListVo;
import com.nightboot.service.RoleService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Validated
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Operation(summary = "查询角色")
    @GetMapping
    public Result<Page<RoleListVo>> findAll(RolePageDto dto) {
        return Result.success(roleService.findAll(dto));
    }

    @Operation(summary = "查询角色")
    @GetMapping("{id}")
    public Result<RoleInfoVo> findOne(@PathVariable("id") @ApiParam(value = "角色ID") String roleId){
        RoleInfoVo roleInfoVo = roleService.queryRoleById(roleId);
        return Result.success(roleInfoVo);
    }

    @Operation(summary = "保存角色")
    @PostMapping
    public Result<Void> save(@RequestBody SaveRoleDto dto) {
        roleService.save(dto);
        return Result.success();
    }

    @Operation(summary = "更新角色")
    @PutMapping
    public Result<Void> update(@RequestBody UpdateRoleDto dto) {
        roleService.update(dto);
        return Result.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") @ApiParam(value = "角色ID") String id) {
        roleService.del(id);
        return Result.success();
    }

    @Operation(summary = "修改角色状态")
    @PutMapping("/setRoleStatus")
    public Result<Void> setRoleStatus(@RequestBody ChangeRoleStatusDto dto){
        roleService.setRoleStatus(dto);
        return Result.success();
    }
}
