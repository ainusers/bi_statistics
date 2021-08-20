package com.read.data.bi_statistics.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/5/28 14:52
 * @description:表格dao
 */
@Mapper
public interface TableMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/28 16:37
      * @description:获取当前表格数据sql
      */
    @Select("select * from bi_chart_info where id = #{id} and data_type = 40")
    Map<String,Object> getTableInfo(String id);


    /**
     * @author: tianyong
     * @time: 2019/5/28 14:52
     * @description:获取当前表格字段集合
     */
    @Select("SELECT column_name from bi_chart_column where chart_id = #{id} and enable = 1 and is_del = 0 order by sort")
    List<Object> getTableColumn(String id);


}
