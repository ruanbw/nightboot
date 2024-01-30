package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.domain.Result;
import com.nightboot.domain.po.GoodsPo;
import com.nightboot.domain.req.goods.ChangeGoodsStatusDto;
import com.nightboot.domain.req.goods.GoodsPageDto;
import com.nightboot.domain.req.goods.SaveGoodsDto;
import com.nightboot.domain.req.goods.UpdateGoodsDto;
import com.nightboot.domain.res.goods.GoodsInfoVo;
import com.nightboot.domain.res.goods.GoodsListVo;
import com.nightboot.mapper.GoodsMapper;
import com.nightboot.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsPo> implements GoodsService {

    @Override
    public Page<GoodsListVo> findAll(GoodsPageDto dto) {
        Page<GoodsListVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<GoodsListVo> list = baseMapper.findAll(page, dto);
        return page.setRecords(list);
    }

    @Override
    public GoodsInfoVo findOne(String id) {
        GoodsPo goods = getById(id);
        if(Objects.isNull(goods) || goods.getStatus() == 4){
            throw new SecurityException("商品信息不存在");
        }
        GoodsInfoVo vo = new GoodsInfoVo();
        BeanUtils.copyProperties(goods,vo);
        // TODO 从OSS查询商品图片
        vo.setImgs(new ArrayList<>());
        return vo;
    }

    @Override
    public void save(SaveGoodsDto dto) {
        GoodsPo newGoods = new GoodsPo();
        BeanUtils.copyProperties(dto, newGoods);
        newGoods.setId(IdUtils.nextId());
        String userId = SecurityUtils.getUserId();
        newGoods.setUserId(userId);
        newGoods.setCreateBy(userId);
        save(newGoods);
    }

    @Override
    public void update(UpdateGoodsDto dto) {
        QueryWrapper<GoodsPo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", dto.getId());
        wrapper.eq("user_id", dto.getUserId());
        wrapper.ne("status",4);
        GoodsPo goods = getOne(wrapper);
        if(Objects.isNull(goods)) {
            throw new RuntimeException("商品不存在");
        }
        BeanUtils.copyProperties(dto, goods);
        goods.setUpdateBy(SecurityUtils.getUserId());
        updateById(goods);
    }

    @Override
    public void del(String goodsId) {
        GoodsPo goods = getById(goodsId);
        if(Objects.isNull(goods)){
            throw new SecurityException("商品信息不存在");
        }
        goods.setStatus(4);
        goods.setUpdateBy(SecurityUtils.getUserId());
        updateById(goods);
    }

    @Override
    public void changeStatus(ChangeGoodsStatusDto dto) {
        GoodsPo goods = getById(dto.getId());
        if(Objects.isNull(goods)){
            throw new SecurityException("商品信息不存在");
        }
        goods.setStatus(dto.getStatus());
        goods.setUpdateBy(SecurityUtils.getUserId());
        updateById(goods);
    }
}
