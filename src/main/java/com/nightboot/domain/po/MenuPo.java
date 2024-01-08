package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;
@Data
@TableName("sys_menu")
public class MenuPo extends BaseEntity {

    private String id;
    private String parentId;
    private String menuName;
    private String path;
    private String component;
    private String permission;
    private Integer type;
    private String icon;
    private Integer orderNo;
    private String hidden;
    private String redirect;
    private Integer status;
    private Integer keepAlive;
    private Integer show;
    private String title;
    private Integer hideChildrenInMenu;
    private Integer hideMenu;
    private Integer hideBreadcrumb;
    private Integer currentActiveMenu;
    private String remark;
    private Integer isExt;
}
