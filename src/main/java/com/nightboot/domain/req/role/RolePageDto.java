package com.nightboot.domain.req.role;

import com.nightboot.domain.PageDto;
import lombok.Data;

@Data
public class RolePageDto extends PageDto {

    private String roleName;

    private Integer status;
}
