package com.nightboot.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页参数")
public class PageDto {


    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数", required = true)
    private Integer pageSize;
}
