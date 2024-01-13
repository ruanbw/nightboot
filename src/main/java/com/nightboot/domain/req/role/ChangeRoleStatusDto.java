package com.nightboot.domain.req.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改角色状态")
public class ChangeRoleStatusDto {

    @ApiModelProperty(value = "角色ID", required = true)
    private String id;

    @ApiModelProperty(value = "状态 0正常 1禁用", required = true)
    private Integer status;
}
