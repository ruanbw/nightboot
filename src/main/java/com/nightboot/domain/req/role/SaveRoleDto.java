package com.nightboot.domain.req.role;

import lombok.Data;

@Data
public class SaveRoleDto {

    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private String remark;

}
