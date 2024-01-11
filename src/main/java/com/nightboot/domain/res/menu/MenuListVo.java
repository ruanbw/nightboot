package com.nightboot.domain.res.menu;

import lombok.Data;

import java.util.List;

@Data
public class MenuListVo {

    private String id;
    private String parentId;
    private String title;
    private String icon;
    private String path;
    private String menuName;
    private String component;
    private String redirect;
    private Integer hideChildrenInMenu;
    private Integer hideMenu;
    private Integer hideBreadcrumb;
    private String currentActiveMenu;
    private Integer status;
    private String remark;
    private Integer orderNo;
    private Integer type;
    private String permission;
    private Integer keepAlive;
    private Integer hidden;
    private Integer isExt;
    private Integer isShow;
    private MetaVo meta;
    private List<MenuListVo> children;
}
