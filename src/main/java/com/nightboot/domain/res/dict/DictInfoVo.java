package com.nightboot.domain.res.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "字典信息")
public class DictInfoVo {

    @ApiModelProperty(value = "字典id")
    private String id;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "字典状态")
    private Integer status;

    @ApiModelProperty(value = "字典备注")
    private String remark;

}
