package com.nightboot.domain.req.menu;

import com.nightboot.domain.res.menu.MenuListVo;
import com.nightboot.domain.res.menu.MetaVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("更新菜单")
@Data
public class UpdateMenuDto {

    @ApiModelProperty(value = "菜单ID",required = true)
    private String id;

    @ApiModelProperty(value = "父菜单ID")
    private String parentId;

    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    @ApiModelProperty(value = "是否隐藏子菜单")
    private Integer hideChildrenInMenu;

    @ApiModelProperty(value = "是否隐藏菜单")
    private Integer hideMenu;

    @ApiModelProperty(value = "是否隐藏面包屑")
    private Integer hideBreadcrumb;

    @ApiModelProperty(value = "当前激活菜单")
    private String currentActiveMenu;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @ApiModelProperty(value = "菜单类型")
    private Integer type;

    @ApiModelProperty(value = "菜单权限标识")
    private String permission;

    @ApiModelProperty(value = "是否缓存")
    private Integer keepAlive;

    @ApiModelProperty(value = "是否隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "是否外链")
    private Integer isExt;

    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    @ApiModelProperty(value = "元数据")
    private MetaVo meta;

    @ApiModelProperty(value = "子菜单")
    private List<MenuListVo> children;

}
