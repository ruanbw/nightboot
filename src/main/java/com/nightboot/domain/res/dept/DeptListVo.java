package com.nightboot.domain.res.dept;

import com.nightboot.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class DeptListVo extends BaseEntity {

    private String id;
    private String parentId;
    private String deptName;
    private Integer orderNo;
    private String remark;
    private Integer status;
    private List<DeptListVo> children;
}
