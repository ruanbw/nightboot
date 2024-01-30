package com.nightboot.domain.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(value = "保存用户")
public class SaveUserDto {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotBlank(message = "角色角色id列表不能为空")
    @ApiModelProperty(value = "角色id列表", required = true)
    private List<String> roleIds;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

    @NotBlank(message = "部门ID不能为空")
    @ApiModelProperty(value = "部门ID", required = true)
    private String deptId;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @ApiModelProperty(value = "备注")
    private String remark;

}
