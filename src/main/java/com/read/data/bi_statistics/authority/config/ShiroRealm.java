package com.read.data.bi_statistics.authority.config;

import com.read.data.bi_statistics.authority.entity.UserInfo;
import com.read.data.bi_statistics.authority.service.UserService;
import com.read.data.bi_statistics.authority.utils.JwtUtil;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/8/14 16:54
 * @description:
 */
@Component
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;


    /**
      * @author: tianyong
      * @time: 2019/8/14 17:02
      * @description:用户鉴权
      */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 大坑！ 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //定义该Realm可以处理哪个类型的token
        return token instanceof JwtToken;
    }


    /**
      * @author: tianyong
      * @time: 2019/8/14 17:01
      * @description:用户认证
      */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        //获取token
        String token = (String) authenticationToken.getCredentials();
        //获取token中的用户名
        String tokenUsername = JwtUtil.getTokenUsername(token);
        if (tokenUsername == null) {
            loggers.error("token无效!");
            throw new AuthenticationException("token无效!");
        }
        //判断用户是否存在
        UserInfo userInfo = userService.queryUserInfoByUsername(tokenUsername);
        if(userInfo == null){
            loggers.error("用户不存在!");
            throw new StatusCodeBO(CodeInfo.USER_NOT_EXIST);
        }
        //校验token是否正确
        JwtUtil.verify(token,tokenUsername,userInfo.getPassword());
        return new SimpleAuthenticationInfo(token,token,getName());
    }
}
