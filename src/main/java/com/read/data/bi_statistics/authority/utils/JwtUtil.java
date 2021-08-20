package com.read.data.bi_statistics.authority.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import org.apache.shiro.authc.AuthenticationException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.EXPIRE_TIME;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.TOKEN_USERNAME;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/8/14 15:10
 * @description:JWT工具类
 */
public class JwtUtil {


    /**
      * @author: tianyong
      * @time: 2019/8/14 15:15
      * @description:校验token是否正确
      */
    public static boolean verify(String token, String username, String secret) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withClaim(TOKEN_USERNAME, username)
                .build();
        //效验token
        try {
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            loggers.error("token已过期!", e);
            throw new AuthenticationException("token已过期!");
        } catch (SignatureVerificationException e) {
            loggers.error("用户名或者密码错误!", e);
            throw new StatusCodeBO(CodeInfo.ACCESS_USERINFO_ERROR);
        }
    }


    /**
      * @author: tianyong
      * @time: 2019/8/14 15:23
      * @description:返回加密后的token (指定时间后过期)
      */
    public static String sign(String username, String secret) {
        try{
            return JWT.create()
                    .withClaim(TOKEN_USERNAME, username)
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                    .sign(Algorithm.HMAC256(secret));
        } catch (Exception e) {
            return null;
        }
    }


    /**
      * @author: tianyong
      * @time: 2019/8/14 16:19
      * @description:获取token中的username
      */
    public static String getTokenUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(TOKEN_USERNAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }



    /**
      * @author: tianyong
      * @time: 2019/8/21 15:02
      * @description:判断字符串是否为汉字
      */
    public static boolean checkChar(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
