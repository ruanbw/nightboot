package com.nightboot.domain.res.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleInfoVo {

    private String id;
    private String roleName;
    private String roleValue;
    private Integer orderNo;
    private List<String> menu;
}
