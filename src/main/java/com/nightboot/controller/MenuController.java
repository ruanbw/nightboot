package com.nightboot.controller;

import com.nightboot.domain.Result;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuInfoVo;
import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.service.MenuService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @Operation(summary = "查询菜单列表")
    @GetMapping
    public Result<List<MenuListVo>> findAll(MenuPageDto dto){
        return Result.success(menuService.findAll(dto));
    }

    @Operation(summary="查询菜单列表")
    @GetMapping("getMenuList")
    public Result<List<MenuListVo>> getMenuList(){
        return Result.success(menuService.getMenuList());
    }

    @Operation(summary = "更新菜单")
    @PutMapping
    public Result<Void> update(@RequestBody @Validated UpdateMenuDto dto){
        menuService.update(dto);
        return Result.success();
    }

    @Operation(summary = "保存菜单")
    @PostMapping
    public Result<Void> save(@RequestBody @Validated SaveMenuDto dto){
        menuService.save(dto);
        return Result.success();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") @ApiParam(value = "菜单ID") String id){
        menuService.del(id);
        return Result.success();
    }

    @Operation(summary = "根据ID查询菜单信息")
    @GetMapping("{id}")
    public Result<MenuInfoVo> findOne(@PathVariable("id") @ApiParam(value = "菜单ID") String id){
        return Result.success(menuService.findOne(id));
    }
}
