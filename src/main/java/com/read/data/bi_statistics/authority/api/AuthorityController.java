package com.read.data.bi_statistics.authority.api;

import com.read.data.bi_statistics.authority.config.JwtToken;
import com.read.data.bi_statistics.authority.service.UserService;
import com.read.data.bi_statistics.cms.config.ControllerLogConfig;
import com.read.data.bi_statistics.cms.entity.ReturnBone;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import static com.read.data.bi_statistics.authority.utils.JwtUtil.sign;
import static com.read.data.bi_statistics.cms.common.Utils.success;

/**
 * @author: tianyong
 * @Time: 2019/8/15 11:55
 * @description:权限认证api
 */
@Controller
@RequestMapping("/auth")
public class AuthorityController {


    @Autowired
    private UserService userService;


    /**
     * @author: tianyong
     * @time: 2018/8/14 14:12
     * @description:用户登陆方法
     */
    @CrossOrigin
    @ResponseBody
    @PostMapping({"/login"})
    @ControllerLogConfig(description = "用户登陆方法api")
    public ReturnBone loginUser(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //登陆验证
        subject.login(new JwtToken(sign(username, password)));
        return success(sign(username,password));
    }



    /**
      * @author: tianyong
      * @time: 2019/8/15 16:31
      * @description:用户登出功能
      */
    @CrossOrigin
    @ResponseBody
    @RequiresAuthentication
    @PostMapping("/logout")
    @ControllerLogConfig(description = "用户登出功能api")
    public ReturnBone logout() {
        SecurityUtils.getSubject().logout();
        return success(null);
    }



    /**
     * @author: tianyong
     * @time: 2019/9/10 14:50
     * @description:修改用户密码
     */
    @CrossOrigin
    @ResponseBody
    @RequiresAuthentication
    @PostMapping("/changePwd")
    @ControllerLogConfig(description = "用户修改密码功能api")
    public void updateUserPassword(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("change_password") String change_password){
        userService.updateUserPassword(username,password,change_password);
    }

}
