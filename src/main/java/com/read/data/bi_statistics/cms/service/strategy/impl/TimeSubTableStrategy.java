package com.read.data.bi_statistics.cms.service.strategy.impl;

import com.read.data.bi_statistics.cms.service.strategy.SubTableStrategy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.handle.ChartDataHandle.makeSubTableLists;

/**
 * @author: tianyong
 * @Time: 2019/5/29 18:27
 * @description:时间分表策略
 */
@Service
public class TimeSubTableStrategy implements SubTableStrategy {

    //成员变量
    private String tablename;
    private Map<String,Object> params;


    /**
      * @author: tianyong
      * @time: 2019/6/5 11:13
      * @description:构造赋值
      */
    public TimeSubTableStrategy() {}
    public TimeSubTableStrategy(String tablename,Map<String, Object> params) {
        this.tablename = tablename;
        this.params = params;
    }


    /**
      * @author: tianyong
      * @time: 2019/5/29 18:29
      * @description:策略算法
      */
    public List<String> strategyAlgorithm(){
        return makeSubTableLists(tablename,params);
    }


}
