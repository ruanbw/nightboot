package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.po.GoodsPo;
import com.nightboot.domain.req.goods.GoodsPageDto;
import com.nightboot.domain.res.goods.GoodsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsPo> {

    List<GoodsListVo> findAll(Page<GoodsListVo> page , @Param("q") GoodsPageDto dto);
}
