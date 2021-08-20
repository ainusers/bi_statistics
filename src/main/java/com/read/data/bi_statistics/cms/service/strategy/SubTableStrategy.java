package com.read.data.bi_statistics.cms.service.strategy;


import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/5/29 18:27
 * @description:分表策略接口
 */
public interface SubTableStrategy {


    /**
      * @author: tianyong
      * @time: 2019/5/29 18:29
      * @description:策略算法
      */
    List<String> strategyAlgorithm();


}
