package com.nightboot.domain.req.dept;

import com.nightboot.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("部门分页查询")
public class DeptPageDto extends PageDto {

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "状态 0正常 1禁用")
    private Integer status;
}
