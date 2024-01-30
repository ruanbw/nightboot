package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.po.DictPo;
import com.nightboot.domain.req.dict.DictPageDto;
import com.nightboot.domain.res.dict.DictListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper extends BaseMapper<DictPo> {

    List<DictListVo> findAll(Page<DictListVo> page, @Param("q") DictPageDto dto);

}
