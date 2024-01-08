package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("t_dept")
public class DeptPo extends BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 上级ID
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门排序
     */
    private Integer orderNo;

    /**
     * 部门描述
     */
    private String remark;

    /**
     * 状态 0正常 1停用 2删除
     */
    private Integer status;

}
