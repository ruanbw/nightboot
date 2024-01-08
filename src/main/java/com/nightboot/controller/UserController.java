package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.user.SaveUserDto;
import com.nightboot.domain.req.user.UpdateUserDto;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserPageVo;
import com.nightboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @GetMapping
    public Result<Page<UserPageVo>> findAll(UserPageDto dto) {
        Page<UserPageVo> page = userService.findAll(dto);
        return Result.success(page);
    }

    @PostMapping
    public Result<Void> save(@RequestBody SaveUserDto dto) {
        userService.save(dto);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(UpdateUserDto dto) {
        userService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") String id) {
        userService.del(id);
        return Result.success();
    }

}
