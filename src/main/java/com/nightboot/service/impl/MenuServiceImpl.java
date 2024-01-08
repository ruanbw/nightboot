package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.MenuPo;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.req.menu.SaveMenuDto;
import com.nightboot.domain.req.menu.UpdateMenuDto;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.mapper.MenuMapper;
import com.nightboot.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPo> implements MenuService {

    @Override
    public List<MenuListVo> findAll(MenuPageDto dto) {
        List<MenuListVo> list = this.baseMapper.findAll(dto);
        return buildChildren(null,list);
    }

    @Override
    public void save(SaveMenuDto dto) {
        MenuPo newMenu = new MenuPo();
        BeanUtils.copyProperties(dto,newMenu);
        newMenu.setId(IdUtils.nextId());
        newMenu.setCreateBy(SecurityUtils.getUserId());
        save(newMenu);
    }

    @Override
    public void update(UpdateMenuDto dto) {
        MenuPo menu = getById(dto.getId());
        if(StringUtils.isNull(menu)){
            throw new ServiceException("菜单不存在");
        }
        BeanUtils.copyProperties(dto,menu);
        menu.setUpdateBy(SecurityUtils.getUserId());
        updateById(menu);
    }

    @Override
    public void del(String id) {
        MenuPo menu = getById(id);
        if(StringUtils.isNull(menu)){
            throw new ServiceException("菜单不存在");
        }
        MenuPo childrenMenu = query().eq("parent_id", id).one();
        if(StringUtils.isNotNull(childrenMenu)){
            throw new ServiceException("请先删除子级");
        }
        menu.setStatus(2);
        menu.setUpdateBy(SecurityUtils.getUserId());
        updateById(menu);
    }

    private List<MenuListVo> buildChildren(String parentId, List<MenuListVo> allList) {
        List<MenuListVo> voList = new ArrayList<>();
        for (MenuListVo item : allList) {
            //如果相等
            if (StringUtils.equals(item.getParentId(), parentId)) {
                //递归，自己调自己
                item.setChildren(buildChildren(item.getId(), allList));
                voList.add(item);
            }
        }
        return voList;
    }
}
