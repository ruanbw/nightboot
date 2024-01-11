package com.nightboot.controller;

import com.nightboot.domain.Result;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping
    public Result<List<MenuListVo>> findAll(MenuPageDto dto){
        return Result.success(menuService.findAll(dto));
    }

    @GetMapping("getMenuList")
    public Result<List<MenuListVo>> getMenuList(MenuPageDto dto){
        return Result.success(menuService.findAll(dto));
    }

    @PostMapping
    public Result<Void> save(@RequestBody SaveMenuDto dto){
        menuService.save(dto);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody  UpdateMenuDto dto){
        menuService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") String id){
        menuService.del(id);
        return Result.success();
    }
}
