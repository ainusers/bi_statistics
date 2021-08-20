package com.read.data.bi_statistics.cms.service;

import org.springframework.cache.annotation.Cacheable;

/**
  * @author: tianyong
  * @time: 2019/5/16 17:22
  * @description:组件接口
  */
public interface ComponentService {

    /**
      * @author: tianyong
      * @time: 2019/5/17 16:21
      * @description:查询组件信息
      */
    @Cacheable(key = "'componentData_'+#root.methodName+'_'+#root.args",value = "redis-cache-always")
    Object getLogicComponentsByPageId(String pageId);
}
