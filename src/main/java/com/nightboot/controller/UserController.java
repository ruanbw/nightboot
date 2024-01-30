package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserInfoVo;
import com.nightboot.domain.res.user.UserPageVo;
import com.nightboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    @Operation(summary = "查询用户列表")
    public Result<Page<UserPageVo>> findAll(UserPageDto dto) {
        Page<UserPageVo> page = userService.findAll(dto);
        return Result.success(page);
    }

    @GetMapping("{userId}")
    @Operation(summary = "根据ID查询用户")
    public Result<UserInfoVo> findOne(@PathVariable("userId") @ApiParam(value = "用户ID", required = true) String userId) {
        return Result.success(userService.findOne(userId));
    }

    @PostMapping
    @Operation(summary = "保存用户信息")
    public Result<Void> save(@RequestBody @Validated SaveUserDto dto) {
        userService.save(dto);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "更新用户信息")
    public Result<Void> update(@RequestBody @Validated UpdateUserDto dto) {
        userService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{userId}")
    @Operation(summary = "删除用户")
    public Result<Void> del(@PathVariable("userId") @ApiParam(value = "用户ID") String userId) {
        userService.del(userId);
        return Result.success();
    }

}
