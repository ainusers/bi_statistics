package com.read.data.bi_statistics.cms.config;


import java.lang.annotation.*;

/**
 * @author: tianyong
 * @Time: 2018/9/1 18:36
 * @description:自定义注解_拦截controller
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD})
public @interface ControllerLogConfig {

    String description() default "";

}
