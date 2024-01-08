package com.nightboot.domain.res.user;

import lombok.Data;

@Data
public class UserPageVo {

    private String id;

    private String username;

    private String nickName;

    private Integer status;

    private String deptName;

    private String createTime;

    private String updateTime;

    private String createBy;

    private String updateBy;
}
