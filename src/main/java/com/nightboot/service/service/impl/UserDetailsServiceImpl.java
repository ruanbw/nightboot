package com.nightboot.service.service.impl;

import com.nightboot.domain.LoginUser;
import com.nightboot.domain.po.SysUserPo;
import com.nightboot.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUserPo user = userService.loadUserByUsername(username);
        if (Objects.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new RuntimeException("登录用户：" + username + " 不存在.");
        }
        else if (user.getStatus().equals(2))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new RuntimeException("登录用户：" + username + " 已被删除.");
        }
        else if (user.getStatus().equals(1))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new RuntimeException("登录用户：{} 已被停用");
        }

        // TODO 查询用户权限及角色
        Set<String> set = new HashSet<>();
        set.add("system:user:list");

        return new LoginUser(user.getId(), user, set);
    }

}
