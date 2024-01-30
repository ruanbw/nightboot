package com.nightboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nightboot.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_user")
public class UserPo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 0:正常 1:冻结 2:删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门ID
     */
    private String deptId;

}
