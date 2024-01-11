package com.nightboot.service;

import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.menu.MenuListVo;

import java.util.List;

public interface MenuService {

    List<MenuListVo> findAll(MenuPageDto dto);

    List<MenuListVo> getMenuList();

    void save(SaveMenuDto dto);

    void update(UpdateMenuDto dto);

    void del(String id);
}
