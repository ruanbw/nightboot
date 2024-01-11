package com.nightboot.domain.res.dept;

import lombok.Data;

@Data
public class DeptInfoVo {

    private String id;
    private String parentId;
    private String deptName;
    private Integer orderNo;
    private String remark;
    private Integer status;
}
