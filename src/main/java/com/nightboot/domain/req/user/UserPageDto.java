package com.nightboot.domain.req.user;

import com.nightboot.domain.PageDto;
import lombok.Data;

@Data
public class UserPageDto extends PageDto {

    private String username;

    private String nickName;

    private Integer status;

    private String email;

    private String deptId;

}
