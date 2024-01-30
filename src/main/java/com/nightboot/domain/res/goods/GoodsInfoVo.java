package com.nightboot.domain.res.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("商品详情")
public class GoodsInfoVo {

    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品描述")
    private String remark;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品图片")
    private List<String> imgs;

    @ApiModelProperty(value = "状态 0正常 1审核 2下架 3卖出 4删除")
    private Integer status;

}
