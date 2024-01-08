package com.nightboot.domain.req.menu;

import lombok.Data;

@Data
public class MenuPageDto {

    private String parentId;
    private String menuName;
    private String path;
    private String component;
    private String permission;
    private Integer type;
    private String icon;
    private Integer orderNo;
    private String keepAlive;
    private String hidden;
    private String redirect;
    private Integer status;

}
