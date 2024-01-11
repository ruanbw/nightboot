package com.nightboot.domain.req.role;

import lombok.Data;

import java.util.List;

@Data
public class SaveRoleDto {

    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private String remark;

    private List<String> menu;

}
