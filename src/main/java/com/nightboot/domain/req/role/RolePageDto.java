package com.nightboot.domain.req.role;

import com.nightboot.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("角色分页查询")
public class RolePageDto extends PageDto {

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色状态 0正常 1禁用")
    private Integer status;
}
