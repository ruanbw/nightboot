package com.nightboot.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.domain.po.SysUserPo;
import com.nightboot.domain.req.sysuser.SysUserPageDto;
import com.nightboot.domain.res.sysuser.SysUserPageVo;
import com.nightboot.mapper.SysUserMapper;
import com.nightboot.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPo> implements SysUserService {

    @Override
    public SysUserPo loadUserByUsername(String username) {
        return query().eq("username",username).one();
    }

    @Override
    public Page<SysUserPageVo> findAll(SysUserPageDto dto) {
        Page<SysUserPageVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<SysUserPageVo> list = this.baseMapper.findAll(page,dto);
        page.setRecords(list);
        return page;
    }
}
