package com.read.data.bi_statistics.authority.filter;

import com.read.data.bi_statistics.authority.config.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/8/15 11:54
 * @description:jwt过滤器 (ShiroConfig中使用)
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {


    /**
      * @author: tianyong
      * @time: 2019/8/20 16:08
      * @description:判断用户是否想要登入(检测header里面是否包含Authorization字段即可)
      */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }


    /**
      * @author: tianyong
      * @time: 2019/8/16 16:53
      * @description:登录认证（一）
      */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
      * @author: tianyong
      * @time: 2019/8/15 15:09
      * @description:认证token、登录（二）
      */
    @Override
    public boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization == null){
            loggers.error("获取token失败!");
            throw new AuthenticationException("获取token失败!");
        }
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


}
