package com.nightboot.domain.bo.rolemenu;

import lombok.Data;

@Data
public class RoleMenuBo {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 权限类型  0:菜单;1:按钮;2:接口
     */
    private Integer type;

    /**
     * 权限名称
     */
    private String menuName;
}
