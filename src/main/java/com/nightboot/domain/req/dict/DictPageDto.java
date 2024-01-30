package com.nightboot.domain.req.dict;

import com.nightboot.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "字典分页查询")
public class DictPageDto extends PageDto {

    @ApiModelProperty(value = "字典名称")
    private String dictName;
}
