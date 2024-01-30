package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.goods.ChangeGoodsStatusDto;
import com.nightboot.domain.req.goods.GoodsPageDto;
import com.nightboot.domain.req.goods.SaveGoodsDto;
import com.nightboot.domain.req.goods.UpdateGoodsDto;
import com.nightboot.domain.res.goods.GoodsInfoVo;
import com.nightboot.domain.res.goods.GoodsListVo;
import com.nightboot.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "商品管理")
@Validated
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping
    @ApiOperation("分页查询商品")
    public Result<Page<GoodsListVo>> findAll(GoodsPageDto dto) {
        return Result.success(goodsService.findAll(dto));
    }

    @GetMapping("{id}")
    @ApiOperation("根据ID查询商品信息")
    public Result<GoodsInfoVo> findOne(@PathVariable("id") @ApiParam(value = "物品ID") String id) {
        return Result.success(goodsService.findOne(id));
    }

    @PostMapping
    @ApiOperation("添加商品")
    public Result<Void> save(@RequestBody SaveGoodsDto dto) {
        goodsService.save(dto);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("更新商品")
    public Result<Void> update(@RequestBody @Validated UpdateGoodsDto dto) {
        goodsService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除商品")
    public Result<Void> del(@PathVariable String id){
        goodsService.del(id);
        return Result.success();
    }

    @PutMapping("changeStatus")
    @ApiOperation("修改商品状态")
    public Result<Void> changeStatus(@RequestBody ChangeGoodsStatusDto dto){
        goodsService.changeStatus(dto);
        return Result.success();
    }

}
