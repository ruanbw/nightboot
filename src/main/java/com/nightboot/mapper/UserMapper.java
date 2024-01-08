package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.UserPo;
import com.nightboot.domain.req.user.UserPageDto;
import com.nightboot.domain.res.user.UserPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<UserPo> {

    List<UserPageVo> findAll(Page<UserPageVo> page, @Param("q") UserPageDto dto);
}
