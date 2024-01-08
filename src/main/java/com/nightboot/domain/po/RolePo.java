package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_role")
public class RolePo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleValue;

    /**
     * 角色状态 0正常 1冻结 2删除
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 备注
     */
    private String remark;

}
