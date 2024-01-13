package com.nightboot.domain.req.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("保存角色")
public class SaveRoleDto {

    @ApiModelProperty(value = "角色名",required = true)
    private String roleName;

    @ApiModelProperty(value = "角色值",required = true)
    private String roleValue;

    @ApiModelProperty(value = "排序",required = true)
    private Integer orderNo;

    @ApiModelProperty(value = "状态",required = true)
    private String remark;

    @ApiModelProperty(value = "菜单",required = true)
    private List<String> menu;

}
