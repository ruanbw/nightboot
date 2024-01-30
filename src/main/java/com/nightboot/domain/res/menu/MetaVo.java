package com.nightboot.domain.res.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("菜单元信息")
public class MetaVo {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "动态路由可打开Tab页数")
    private Integer dynamicLevel;

    @ApiModelProperty(value = "动态路由的实际Path, 即去除路由的动态部分;")
    private String realPath;

    @ApiModelProperty(value = "是否忽略权限，只在权限模式为Role的时候有效")
    private Integer ignoreAuth;

    @ApiModelProperty(value = "可以访问的角色，只在权限模式为Role的时候有效")
    private List<String> roles;

    @ApiModelProperty(value = "是否忽略KeepAlive缓存")
    private Integer ignoreKeepAlive;

    @ApiModelProperty(value = "是否固定标签")
    private Integer affix;

    @ApiModelProperty(value = "内嵌iframe的地址")
    private String frameSrc;

    @ApiModelProperty(value = "指定该路由切换的动画名")
    private String transitionName;

    @ApiModelProperty(value = "隐藏该路由在面包屑上面的显示")
    private Integer hideBreadcrumb;

    @ApiModelProperty(value = "如果该路由会携带参数，且需要在tab页上面显示。则需要设置为true")
    private Integer carryParam;

    @ApiModelProperty(value = "隐藏所有子菜单")
    private Integer hideChildrenInMenu;

    @ApiModelProperty(value = "当前激活的菜单。用于配置详情页时左侧激活的菜单路径")
    private String currentActiveMenu;

    @ApiModelProperty(value = "当前路由不再标签页显示")
    private Integer hideTab;

    @ApiModelProperty(value = "当前路由不再菜单显示")
    private Integer hideMenu;

    @ApiModelProperty(value = "菜单排序，只对第一级有效")
    private Integer orderNo;

    @ApiModelProperty(value = "忽略路由。用于在ROUTE_MAPPING以及BACK权限模式下，生成对应的菜单而忽略路由。2.5.3以上版本有效")
    private Integer ignoreRoute;

    @ApiModelProperty(value = "是否在子级菜单的完整path中忽略本级path。2.5.3以上版本有效")
    private Integer hidePathForChildren;
}
