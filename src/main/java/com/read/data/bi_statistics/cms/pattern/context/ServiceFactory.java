package com.read.data.bi_statistics.cms.pattern.context;

import com.read.data.bi_statistics.cms.service.ChartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/6/26 11:17
 * @description:服务工厂(主要用于动态注入接口实现类)
 */
@Component
public class ServiceFactory implements ApplicationContextAware {


    //定义成员变量
    private static Map<String,ChartService> res;
    private Class clazz;


    //构造函数
    public ServiceFactory() {}
    public ServiceFactory(Class clazz) {
        this.clazz = clazz;
    }


    /**
      * @author: tianyong
      * @time: 2019/6/27 16:04
      * @description:设置上下文参数
      */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        res = applicationContext.getBeansOfType(clazz);
    }


    /**
      * @author: tianyong
      * @time: 2019/6/27 16:05
      * @description:根据标记返回当前接口实现类
      */
    public <T extends ChartService> T getServices(String flag){
        return (T)res.get(flag);
    }



}
