package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.sysuser.SysUserPageDto;
import com.nightboot.domain.res.sysuser.SysUserPageVo;
import com.nightboot.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/sysuser")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @PreAuthorize("@ss.hasPermi('hello')")
    @RequestMapping("/hello")
    public Result<String> hello() {
        log.info("hello");
        return Result.success("hello");
    }


    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping
    public Result<Page<SysUserPageVo>> findAll(SysUserPageDto dto) {
        Page<SysUserPageVo> page = sysUserService.findAll(dto);
        return Result.success(page);
    }

}
