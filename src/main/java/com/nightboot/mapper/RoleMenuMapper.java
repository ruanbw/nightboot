package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nightboot.domain.bo.rolemenu.RoleMenuBo;
import com.nightboot.domain.po.RoleMenuPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenuPo> {

    /**
     * 重写批量新增方法
     */
    void batchSave(@Param("list") List<RoleMenuPo> list);

    /**
     *  查询角色权限信息
     */
    List<RoleMenuBo> queryRolePermissions(@Param("rolelId") String rolelId);
}
