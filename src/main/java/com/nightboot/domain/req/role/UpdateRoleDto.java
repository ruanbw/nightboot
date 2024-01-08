package com.nightboot.domain.req.role;

import lombok.Data;

@Data
public class UpdateRoleDto {

    private String id;
    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private String remark;

}
