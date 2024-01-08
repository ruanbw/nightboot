package com.nightboot.domain.req.dept;

import lombok.Data;

@Data
public class SaveDeptDto {

    private String deptName;
    private String parentId;
    private Integer orderNo;
    private Integer status;
    private String remark;
}
