package com.nightboot.domain.req.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("更新部门")
public class UpdateDeptDto {

    @ApiModelProperty(value = "部门ID")
    private String id;

    @ApiModelProperty(value = "上级部门id")
    private String parentId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @ApiModelProperty(value = "状态 0正常 1禁用")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
