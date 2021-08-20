package com.read.data.bi_statistics.cms.service.strategy.impl;

import com.read.data.bi_statistics.cms.data.bo.SqlBO;
import com.read.data.bi_statistics.cms.service.strategy.ExplainStrategy;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.handle.ChartDataHandle.explainSqlTemplate;

/**
  * @author: tianyong
  * @time: 2019/6/20 15:27
  * @description:sql模板解析策略接口
  */
@Service
public class RegexExplainStrategy implements ExplainStrategy {

    //成员变量
    //sql模板
    private String sqlTemplate;
    //参数集合
    private Map params = new HashMap();


    /**
      * @author: tianyong
      * @time: 2019/6/20 15:28
      * @description:构造赋值
      */
    public RegexExplainStrategy() {}
    public RegexExplainStrategy(String sqlTemplate, Map params) {
        this.sqlTemplate = sqlTemplate;
        this.params = params;
    }


    /**
      * @author: tianyong
      * @time: 2019/6/20 15:28
      * @description: sql模板解析算法
      */
    public List<SqlBO> strategyAlgorithm(){
        return explainSqlTemplate(sqlTemplate,params);
    }


}
