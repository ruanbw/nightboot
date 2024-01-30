package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_dict_val")
public class DictValPo {

    /**
     * id
     */
    private String id;

    /**
     * 上级Id
     */
    private String parentId;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 键
     */
    private String dictKey;

    /**
     * 值
     */
    private String dictVal;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 备注
     */
    private String remark;

}
