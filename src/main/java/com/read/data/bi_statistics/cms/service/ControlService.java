package com.read.data.bi_statistics.cms.service;

import org.springframework.cache.annotation.Cacheable;

/**
  * @author: tianyong
  * @time: 2019/5/21 20:14
  * @description:控件接口
  */
public interface ControlService {

    /**
     * @author: tianyong
     * @time: 2019/5/21 19:34
     * @description:查询当前页面的控件数据(通过组件关联)
     */
    @Cacheable(key = "'controlData_'+#root.methodName+'_'+#root.args",value = "redis-cache-always")
    Object getCurrentControl(String pageId);

}
