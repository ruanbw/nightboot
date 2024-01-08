package com.nightboot.domain.req.dept;

import lombok.Data;

@Data
public class UpdateDeptDto {

    private String id;
    private String deptName;
    private Integer orderNo;
    private Integer status;
    private String remark;
}
