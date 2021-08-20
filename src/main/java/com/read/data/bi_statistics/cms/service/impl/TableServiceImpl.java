package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.CommonMapper;
import com.read.data.bi_statistics.cms.dao.TableMapper;
import com.read.data.bi_statistics.cms.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
  * @author: tianyong
  * @time: 2019/5/28 15:00
  * @description:表格服务
  */
@Service
public class TableServiceImpl implements TableService {


    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private CommonMapper commonMapper;


    /**
     * @author: tianyong
     * @time: 2019/5/28 16:37
     * @description:获取当前表格数据sql
     */
    public Map<String,Object> getTableInfo(String id){
        return tableMapper.getTableInfo(id);
    }


    /**
     * @author: tianyong
     * @time: 2019/5/28 14:52
     * @description:获取当前表格字段集合
     */
    public List<Object> getTableColumn(String id){
        return tableMapper.getTableColumn(id);
    }


    /**
      * @author: tianyong
      * @time: 2019/5/28 15:01
      * @description:获取当前表格数据
      */
    public List<LinkedHashMap<String, Object>> getTableData(String sql) {
        return commonMapper.queryDataForSortList(sql);
    }


}
