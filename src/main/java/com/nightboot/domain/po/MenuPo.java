package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_menu")
public class MenuPo extends BaseEntity {

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
}
