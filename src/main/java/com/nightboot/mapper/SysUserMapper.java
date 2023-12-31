package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.SysUserPo;
import com.nightboot.domain.req.sysuser.SysUserPageDto;
import com.nightboot.domain.res.sysuser.SysUserPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUserPo> {

    List<SysUserPageVo> findAll(Page<SysUserPageVo> page, @Param("q") SysUserPageDto dto);
}
