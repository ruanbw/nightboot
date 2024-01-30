package com.nightboot.domain.req.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("商品更新")
public class UpdateGoodsDto {

    @NotBlank(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id", required = true)
    private String id;

    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @NotBlank(message = "商品标题不能为空")
    @ApiModelProperty(value = "商品标题", required = true)
    private String title;

    @NotBlank(message = "商品描述不能为空")
    @ApiModelProperty(value = "商品描述", required = true)
    private String remark;

    @NotNull(message = "商品价格不能为空")
    @ApiModelProperty(value = "商品价格", required = true)
    private BigDecimal price;

}
