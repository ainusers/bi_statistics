package com.read.data.bi_statistics.cms.service.strategy.impl;

import com.read.data.bi_statistics.cms.service.strategy.SubTableStrategy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.handle.ChartDataHandle.dynamicTableName;

/**
  * @author: tianyong
  * @time: 2019/7/30 11:16
  * @description:动态表名策略
  */
@Service
public class DynamicTableStrategy implements SubTableStrategy {

    //成员变量
    private String tablename;
    private Map<String,Object> params;


    /**
      * @author: tianyong
      * @time: 2019/7/30 11:21
      * @description:构造赋值
      */
    public DynamicTableStrategy() {}
    public DynamicTableStrategy(String tablename, Map<String, Object> params) {
        this.tablename = tablename;
        this.params = params;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/30 11:21
      * @description:策略算法
      */
    public List<String> strategyAlgorithm(){
        return dynamicTableName(tablename,params);
    }


}
