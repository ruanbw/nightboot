package com.nightboot.common.security.handle;

import com.alibaba.fastjson2.JSON;
import com.nightboot.common.utils.ServletUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.LoginUser;
import com.nightboot.domain.Result;
import com.nightboot.service.TokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * 
 * @author nightboot
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{

    @Resource
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            // tokenService.delLoginUser(loginUser.getToken()); // 允许多终端登陆
            tokenService.delLoginUser(loginUser.getToken(), loginUser.getUser().getId()); // 单终端登陆
                    }
        ServletUtils.renderString(response, JSON.toJSONString(Result.success("退出成功")));
    }
}
