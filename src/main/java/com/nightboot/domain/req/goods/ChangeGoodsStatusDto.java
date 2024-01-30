package com.nightboot.domain.req.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("更改商品状态")
public class ChangeGoodsStatusDto {

    @NotBlank(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品ID",required = true)
    private String id;

    @NotNull(message = "商品状态不能为空")
    @ApiModelProperty(value = "商品状态 0正常 1审核 2下架 3售出 4删除",required = true)
    private Integer status;
}
