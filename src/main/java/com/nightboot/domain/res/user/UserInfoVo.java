package com.nightboot.domain.res.user;

import com.nightboot.domain.po.RolePo;
import com.nightboot.domain.res.role.RoleInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户信息")
public class UserInfoVo {

    @ApiModelProperty(value = "用户ID")
    private String id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门")
    private String deptId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "角色")
    private List<RoleInfoVo> roles;
}
