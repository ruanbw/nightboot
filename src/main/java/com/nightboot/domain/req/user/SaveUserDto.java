package com.nightboot.domain.req.user;

import lombok.Data;
@Data
public class SaveUserDto {

    private String username;
    private String password;
    private String nickName;

}
