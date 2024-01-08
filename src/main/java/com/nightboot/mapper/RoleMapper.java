package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.req.role.RolePageDto;
import com.nightboot.domain.res.role.RoleListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<RolePo> {

    List<RoleListVo> findAll(Page page, @Param("q") RolePageDto dto);
}
