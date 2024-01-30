package com.nightboot.domain.req.dictval;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "新增字段类型信息")
public class SaveDictValDto {

    @NotBlank(message = "字典类型上级id不能为空")
    @ApiModelProperty(value = "字典类型上级id",required = true)
    private String parentId;

    @NotBlank(message = "所属字典id不能为空")
    @ApiModelProperty(value = "所属字典id",required = true)
    private String dictId;

    @NotBlank(message = "字典类型键不能为空")
    @ApiModelProperty(value = "键",required = true)
    private String dictKey;

    @NotBlank(message = "字典类型值不能为空")
    @ApiModelProperty(value = "值",required = true)
    private String dictVal;

    @ApiModelProperty(value = "排序")
    private Integer orderNo;

    @ApiModelProperty(value = "备注")
    private String remark;
}
