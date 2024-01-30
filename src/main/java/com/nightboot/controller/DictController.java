package com.nightboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nightboot.domain.Result;
import com.nightboot.domain.req.dict.DictPageDto;
import com.nightboot.domain.req.dict.SaveDictDto;
import com.nightboot.domain.req.dict.UpdateDictDto;
import com.nightboot.domain.req.dictval.DictValPageDto;
import com.nightboot.domain.req.dictval.SaveDictValDto;
import com.nightboot.domain.req.dictval.UpdateDictValDto;
import com.nightboot.domain.res.dict.DictInfoVo;
import com.nightboot.domain.res.dict.DictListVo;
import com.nightboot.domain.res.dictval.DictValInfoDto;
import com.nightboot.domain.res.dictval.DictValListVo;
import com.nightboot.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "字典相关")
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    private DictService dictService;

    @Operation(summary = "分页查询字典类型")
    @GetMapping
    private Result<Page<DictListVo>> findAll(DictPageDto dto){
        return Result.success(dictService.findAll(dto));
    }

    @Operation(summary = "查询字典类型信息")
    @GetMapping("{id}")
    public Result<DictInfoVo> findOne(@PathVariable("id") @ApiParam(value = "字典id",required = true) String id){
        return Result.success(dictService.findOne(id));
    }

    @Operation(summary = "添加字典类型信息")
    @PostMapping
    private Result<Void> save(@RequestBody @Validated SaveDictDto dto){
        dictService.save(dto);
        return Result.success();
    }

    @Operation(summary = "更新字典字典类型信息")
    @PutMapping
    private Result<Void> update(@RequestBody @Validated UpdateDictDto dto){
        dictService.update(dto);
        return Result.success();
    }

    @Operation(summary = "dictval分页查询字典类型列表")
    @GetMapping("/dictval")
    private Result<Page<DictValListVo>> findDictValAll(DictValPageDto dto){
        Page<DictValListVo> page = dictService.findDictValAll(dto);
        return Result.success(page);
    }

    @Operation(summary = "dictval根据ID查询字典类型信息")
    @GetMapping("/dictval/{id}")
    public Result<DictValInfoDto> findDictValOne(@PathVariable("id") @ApiParam(value = "字典类型id")String id){
        DictValInfoDto dictValOne = dictService.findDictValOne(id);
        return Result.success(dictValOne);
    }

    @Operation(summary = "dictval添加字典类型信息")
    @PostMapping("/dictval")
    public Result<Void> saveDictVal(@RequestBody @Validated SaveDictValDto dto) {
        dictService.saveDictVal(dto);
        return Result.success();
    }

    @Operation(summary = "dictval更新字典类型信息")
    @PutMapping("/dictval")
    public Result<Void> updateDictVal(@RequestBody @Validated UpdateDictValDto dto){
        dictService.updateDictVal(dto);
        return Result.success();
    }

    @Operation(summary = "删除字典类型信息")
    @DeleteMapping("dictval/dictval/{id}")
    public Result<Void> delDictVal(@PathVariable("id") @ApiParam(value = "字典类型ID",required = true) String id){
        dictService.delDictVal(id);
        return Result.success();
    }

    @Operation(summary = "dictval获取所有字典类型信息")
    @GetMapping("/dictval/all")
    public Result<List<DictListVo>> dictValAll(){
        return Result.success(dictService.dictValAll());
    }

}
