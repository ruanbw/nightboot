package com.nightboot.domain.req.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("商品保存")
public class SaveGoodsDto {

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
