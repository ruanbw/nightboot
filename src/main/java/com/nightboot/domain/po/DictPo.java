package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import com.nightboot.domain.res.dict.DictListVo;
import lombok.Data;

@Data
@TableName("sys_dict")
public class DictPo extends BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 0正常 1停用
     */
    private Integer status;

}
