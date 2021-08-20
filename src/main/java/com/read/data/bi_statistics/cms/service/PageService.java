package com.read.data.bi_statistics.cms.service;

import org.springframework.cache.annotation.Cacheable;

/**
  * @author: tianyong
  * @time: 2019/5/21 16:02
  * @description:页面接口
  */
public interface PageService {


    /**
     * @author: tianyong
     * @time: 2019/5/21 16:01
     * @description:查询当前页面数据
     */
    @Cacheable(key = "'pageData_'+#root.methodName+'_'+#root.args",value = "redis-cache-always")
    Object getCurrentPage(String pageId);


}
