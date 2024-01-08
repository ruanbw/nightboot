package com.nightboot.domain.res.role;

import lombok.Data;

@Data
public class RoleListVo {

    private String id;
    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private Integer status;
    private String remark;
    private String createTime;
    private String updateTime;
    private String createBy;
    private String updateBy;
}
