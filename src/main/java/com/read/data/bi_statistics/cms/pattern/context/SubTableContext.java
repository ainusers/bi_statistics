package com.read.data.bi_statistics.cms.pattern.context;

import com.read.data.bi_statistics.cms.service.strategy.SubTableStrategy;
import java.util.List;


/**
 * @author: tianyong
 * @Time: 2019/5/29 18:25
 * @description:分表上下文
 */
public class SubTableContext {


    //分表策略对象
    private SubTableStrategy subTableStrategy;


    public SubTableContext() {}


    //构造函数、传入具体策略参数
    public SubTableContext(SubTableStrategy strategy) {
        this.subTableStrategy = strategy;
    }


    //创建策略实例
    public static SubTableContext getInstance(SubTableStrategy strategy){
        return new SubTableContext(strategy);
    }


    //切换策略
    public void setStrategy(SubTableStrategy strategy) {
        this.subTableStrategy = strategy;
    }


    //调用策略方法
    public List<String> strategy(){
        return subTableStrategy.strategyAlgorithm();
    }


}
