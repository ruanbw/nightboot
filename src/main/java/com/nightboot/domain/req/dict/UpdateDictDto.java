package com.nightboot.domain.req.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "更新字典")
public class UpdateDictDto {

    @NotBlank(message = "字典id")
    @ApiModelProperty(value = "字典id",required = true)
    private String id;

    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty(value = "字典名称",required = true)
    private String dictName;

    @NotBlank(message = "字典类型不能为空")
    @ApiModelProperty(value = "字典类型",required = true)
    private String dictType;

    @ApiModelProperty(value = "字典备注")
    private String remark;
}
