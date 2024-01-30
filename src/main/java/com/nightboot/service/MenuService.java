package com.nightboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.po.MenuPo;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuInfoVo;
import com.nightboot.domain.res.menu.MenuListVo;

import java.util.List;

public interface MenuService extends IService<MenuPo> {

    List<MenuListVo> findAll(MenuPageDto dto);

    MenuInfoVo findOne(String id);

    List<MenuListVo> getMenuList();

    void save(SaveMenuDto dto);

    void update(UpdateMenuDto dto);

    void del(String id);
}
