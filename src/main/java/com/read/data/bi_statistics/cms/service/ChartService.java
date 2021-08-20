package com.read.data.bi_statistics.cms.service;

import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/6/18 11:23
 * @description:图表服务
 */
public interface ChartService {


    /**
      * @author: tianyong
      * @time: 2019/6/19 10:12
      * @description:组装返回对象
      */
    Object buildDTO (String sqlTemplate,Map params);

}
