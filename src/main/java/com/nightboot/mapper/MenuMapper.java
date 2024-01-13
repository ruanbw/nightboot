package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nightboot.domain.po.DeptPo;
import com.nightboot.domain.po.MenuPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.req.menu.MenuPageDto;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.domain.res.menu.MenuListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuPo> {

    List<MenuListVo> findAll(@Param("q") MenuPageDto dto);

    List<MenuListVo> getMenuList(@Param("q") String dto);

}
