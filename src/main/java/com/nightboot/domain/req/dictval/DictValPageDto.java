package com.nightboot.domain.req.dictval;

import com.nightboot.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "字典类型分页查询")
public class DictValPageDto extends PageDto {

    @ApiModelProperty(value = "字典类型所属ID")
    private String dictId;

    @ApiModelProperty(value = "键")
    private String dictKey;
}
