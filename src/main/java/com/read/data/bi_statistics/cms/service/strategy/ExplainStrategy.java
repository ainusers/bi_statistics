package com.read.data.bi_statistics.cms.service.strategy;


import com.read.data.bi_statistics.cms.data.bo.SqlBO;
import java.util.List;

/**
  * @author: tianyong
  * @time: 2019/6/20 15:21
  * @description:解析策略接口
  */
public interface ExplainStrategy {


    /**
      * @author: tianyong
      * @time: 2019/6/20 15:21
      * @description:策略算法
      */
    List<SqlBO> strategyAlgorithm();


}
