package com.read.data.bi_statistics.cms.exception;

import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.entity.ReturnBone;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import static com.read.data.bi_statistics.authority.utils.JwtUtil.checkChar;
import static com.read.data.bi_statistics.cms.common.Utils.error;

/**
 * @author: tianyong
 * @Time: 2019/7/15 15:54
 * @description:状态码配置类
 */
@Controller
public class StatusCodeExceptionHandler implements ErrorController {


    /**
      * @author: tianyong
      * @time: 2019/7/16 11:36
      * @description:指定跳转路径/error
      */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
      * @author: tianyong
      * @time: 2019/7/16 11:37
      * @description:处理错误状态码
      */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception attribute = (Exception)request.getAttribute("javax.servlet.error.exception");
        if(statusCode == 400) {
            return "/400";
        }else if(statusCode == 401) {
            return "/401";
        }else if(statusCode == 403) {
            return "/403";
        }else if(statusCode == 404) {
            return "/404";
        }else if(statusCode == 405) {
            return "/405";
        }else if(statusCode == 500) {
            //处理全局异常无法捕获过滤器中自定义异常问题
            String message = attribute.getCause().getMessage();
            if(checkChar(message)){
                return "/401";
            }else{
                return "/500";
            }
        }else{
            return "/500";
        }
    }


    /**
      * @author: tianyong
      * @time: 2019/7/15 16:14
      * @description:400 请求无效
      */
    @ResponseBody
    @RequestMapping("/400")
    public ReturnBone error400 (){
        return error(CodeInfo.STATUS_CODE_400);
    }


    /**
      * @author: tianyong
      * @time: 2019/8/15 14:16
      * @description:401 用户认证失败
      */
    @ResponseBody
    @RequestMapping("/401")
    public ReturnBone error401 (){
        return error(CodeInfo.STATUS_CODE_401);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/15 16:14
     * @description:403 禁止访问
     */
    @ResponseBody
    @RequestMapping("/403")
    public ReturnBone error403 (){
        return error(CodeInfo.STATUS_CODE_403);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/15 16:14
     * @description:404 请求的网页不存在
     */
    @ResponseBody
    @RequestMapping("/404")
    public ReturnBone error404 (){
        return error(CodeInfo.STATUS_CODE_404);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/15 16:14
     * @description:405 资源被禁止
     */
    @ResponseBody
    @RequestMapping("/405")
    public ReturnBone error405 (){
        return error(CodeInfo.STATUS_CODE_405);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/15 16:14
     * @description:500 内部服务器错误,请联系管理员
     */
    @ResponseBody
    @RequestMapping("/500")
    public ReturnBone error500 (){
        return error(CodeInfo.STATUS_CODE_500);
    }

}