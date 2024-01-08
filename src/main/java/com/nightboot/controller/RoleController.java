package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.role.ChangeRoleStatusDto;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.req.role.SaveRoleDto;
import com.nightboot.domain.req.role.UpdateRoleDto;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.res.role.RoleListVo;
import com.nightboot.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    public Result<Page<RoleListVo>> findAll(RolePageDto dto) {
        return Result.success(roleService.findAll(dto));
    }

    @PostMapping
    public Result<Void> save(@RequestBody SaveRoleDto dto) {
        roleService.save(dto);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody UpdateRoleDto dto) {
        roleService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") String id) {
        roleService.del(id);
        return Result.success();
    }

    @PutMapping("/setRoleStatus")
    public Result<Void> setRoleStatus(@RequestBody ChangeRoleStatusDto dto){
        roleService.setRoleStatus(dto);
        return Result.success();
    }
}
