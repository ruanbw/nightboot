package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role_menu")
public class RoleMenuPo {

    private String id;
    private String roleId;
    private String menuId;
}
