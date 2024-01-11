package com.nightboot.domain.req.role;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleDto {

    private String id;
    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private String remark;
    private List<String> menu;

}
