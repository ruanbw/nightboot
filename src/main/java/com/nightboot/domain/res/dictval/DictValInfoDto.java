package com.nightboot.domain.res.dictval;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "字典类型信息")
public class DictValInfoDto {

    @ApiModelProperty(value = "字典类型id")
    private String id;

    @ApiModelProperty(value = "上级")
    private String parentId;

    @ApiModelProperty(value = "所属字典id")
    private String dictId;

    @ApiModelProperty(value = "键")
    private String dictKey;

    @ApiModelProperty(value = "值")
    private String dictVal;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @ApiModelProperty(value = "备注")
    private String remark;

}
