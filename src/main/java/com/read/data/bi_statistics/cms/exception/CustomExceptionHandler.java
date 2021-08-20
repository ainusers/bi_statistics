package com.read.data.bi_statistics.cms.exception;

import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.entity.ReturnBone;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tianyong
 * @Time: 2019/7/15 16:08
 * @description:自定义异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {


    /**
     * @author: tianyong
     * @time: 2019/7/15 16:10
     * @description:自定义异常处理方法
     */
    @ResponseBody
    @ExceptionHandler({StatusCodeBO.class})
    public ReturnBone HandlerException(StatusCodeBO info){
        return info.makeInfo();
    }


}
