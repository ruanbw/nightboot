package com.nightboot.domain.req.dept;

import com.nightboot.domain.PageDto;
import lombok.Data;

@Data
public class DeptPageDto extends PageDto {

    private String deptName;
    private Integer status;
}
