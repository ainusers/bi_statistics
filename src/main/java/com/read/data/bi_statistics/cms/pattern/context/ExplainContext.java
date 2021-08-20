package com.read.data.bi_statistics.cms.pattern.context;

import com.read.data.bi_statistics.cms.data.bo.SqlBO;
import com.read.data.bi_statistics.cms.service.strategy.ExplainStrategy;
import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/6/20 15:01
 * @description:解析上下文
 */
public class ExplainContext{


    //解析策略对象
    private ExplainStrategy explainStrategy;


    //构造函数、传入具体策略参数
    public ExplainContext() {}
    public ExplainContext(ExplainStrategy explainStrategy) {
        this.explainStrategy = explainStrategy;
    }


    //创建策略实例
    public static ExplainContext getInstance(ExplainStrategy strategy){
        return new ExplainContext(strategy);
    }


    //切换策略
    public void setStrategy(ExplainStrategy strategy) {
        this.explainStrategy = strategy;
    }


    //调用策略方法
    public List<SqlBO> strategy(){
        return explainStrategy.strategyAlgorithm();
    }




}
