package com.read.data.bi_statistics.cms.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
  * @author: tianyong
  * @time: 2019/5/28 14:59
  * @description:表格服务
  */
public interface TableService {


    /**
     * @author: tianyong
     * @time: 2019/5/28 16:37
     * @description:获取当前表格数据sql
     */
    Map<String,Object> getTableInfo(String id);


    /**
     * @author: tianyong
     * @time: 2019/5/28 14:52
     * @description:获取当前表格字段集合
     */
    List<Object> getTableColumn(String id);


    /**
     * @author: tianyong
     * @time: 2019/5/28 14:52
     * @description:获取当前表格数据
     */
    List<LinkedHashMap<String,Object>> getTableData(String sql);


}
