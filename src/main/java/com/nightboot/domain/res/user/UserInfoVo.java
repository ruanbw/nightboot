package com.nightboot.domain.res.user;

import com.nightboot.domain.po.RolePo;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoVo {

    private String id;

    private String avatar;

    private String desc;

    private String nickName;

    private String email;

    private Long deptId;

    private String remark;

    private List<RolePo> roles;
}
