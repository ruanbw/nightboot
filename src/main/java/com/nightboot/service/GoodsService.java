package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.Result;
import com.nightboot.domain.po.GoodsPo;
import com.nightboot.domain.req.goods.ChangeGoodsStatusDto;
import com.nightboot.domain.req.goods.GoodsPageDto;
import com.nightboot.domain.req.goods.SaveGoodsDto;
import com.nightboot.domain.req.goods.UpdateGoodsDto;
import com.nightboot.domain.res.goods.GoodsInfoVo;
import com.nightboot.domain.res.goods.GoodsListVo;

public interface GoodsService extends IService<GoodsPo> {

    Page<GoodsListVo> findAll(GoodsPageDto dto);

    GoodsInfoVo findOne(String id);

    void save(SaveGoodsDto dto);

    void update(UpdateGoodsDto dto);

    void del(String goodsId);

    void changeStatus(ChangeGoodsStatusDto dto);
}
