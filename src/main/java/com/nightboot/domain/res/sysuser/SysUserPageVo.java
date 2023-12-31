package com.nightboot.domain.res.sysuser;

import lombok.Data;

@Data
public class SysUserPageVo {

    private Long id;

    private String username;

    private String nickName;

    private Integer status;

    private String createTime;

    private String updateTime;

    private String createBy;

    private String updateBy;
}
