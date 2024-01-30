package com.nightboot.domain.req.goods;

import com.nightboot.domain.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("商品分页")
public class GoodsPageDto extends PageDto {

    @ApiModelProperty(value = "商品所属用户")
    private String username;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "状态 0正常 1审核 2下架 3卖出 4删除")
    private Integer status;

}
