package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_goods")
public class GoodsPo extends BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 所属用户
     */
    private String userId;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品描述
     */
    private String remark;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 状态 0正常 1审核 2下架 3卖出 4删除
     */
    private Integer status;
}
