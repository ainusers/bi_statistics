package com.read.data.bi_statistics.authority.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: tianyong
 * @Time: 2019/8/14 16:49
 * @description:实现shiro的AuthenticationToken接口(用户认证参数)的类
 */
public class JwtToken implements AuthenticationToken {


    private String token;


    /**
      * @author: tianyong
      * @time: 2019/8/15 15:05
      * @description:构造函数赋值 (jwt过滤器：执行登录操作)
      */
    public JwtToken(String token) {
        this.token = token;
    }


    @Override
    public Object getPrincipal() {
        return token;
    }


    @Override
    public Object getCredentials() {
        return token;
    }
}
