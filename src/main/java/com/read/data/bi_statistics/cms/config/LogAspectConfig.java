package com.read.data.bi_statistics.cms.config;

import com.read.data.bi_statistics.cms.dao.CommonMapper;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/4/9 15:46
 * @description:日志记录AOP实现
 */
@Aspect
@Component
public class LogAspectConfig {


    @Autowired
    private CommonMapper commonMapper;


    /**
     * @author: tianyong
     * @time: 2018/9/2 12:11
     * @description:controller层切点，注解拦截
     */
    @Pointcut("@annotation(com.read.data.bi_statistics.cms.config.ControllerLogConfig)")
    private void controllerAspect(){
        // Do nothing
    }


    /**
      * @author: tianyong
      * @time: 2019/4/9 16:20
      * @description:解析controller层注解的description信息
      */
    private static String getControllerMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ControllerLogConfig controllerLog = method.getAnnotation(ControllerLogConfig.class);
        return controllerLog.description();
    }


    /**
      * @author: tianyong
      * @time: 2019/4/9 16:27
      * @description:请求方法后触发
      */
    @After("controllerAspect()")
    private void doAfterInServiceLayer(JoinPoint joinPoint) {
        String description = getControllerMethodDescription(joinPoint);
        if(StringUtils.isBlank(description)){
            description = "-";
        }
        //调用输出
        this.intoDB(description);
    }


    /**
     * @author: tianyong
     * @time: 2018/9/2 12:14
     * @description:收集参数入库
     */
    private void intoDB(String description){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户信息
        String userName = "";//(String) CommonUtils.getUserName().get("username");
        //获取请求IP
        String ip = request.getRemoteAddr();
        // 获取请求地址
        String requestPath = request.getRequestURI();
        //访问时间
        String comeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //数据入库
        try {
            String insertLogSql = "insert into bi_log_info (username,ip,method,description,createtime)" +
                    "values(\""+userName+"\",\""+ip+"\",\""+requestPath+"\",\""+description+"\",\""+comeTime+"\")";
            //保存数据库
            commonMapper.queryDataForSortList(insertLogSql);
        }  catch (Exception e) {
            //记录本地异常日志
            loggers.error("收集参数入库错误!", e);
            throw new StatusCodeBO(CodeInfo.USER_AUDIT_INTODB_FAIL);
        }
    }
}
