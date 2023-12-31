package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.SysUserPo;
import com.nightboot.domain.req.sysuser.SysUserPageDto;
import com.nightboot.domain.res.sysuser.SysUserPageVo;

public interface SysUserService {

    Page<SysUserPageVo> findAll(SysUserPageDto dto);

    SysUserPo loadUserByUsername(String username);
}
