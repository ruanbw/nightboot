package com.nightboot.domain.req.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("保存部门")
public class SaveDeptDto {

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "上级部门ID")
    private String parentId;

    @ApiModelProperty(value = "排序号")
    private Integer orderNo;

    @ApiModelProperty(value = "状态 0正常 1停用")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
