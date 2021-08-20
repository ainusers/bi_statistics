package com.read.data.bi_statistics.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
  * @author: tianyong
  * @time: 2019/5/28 16:58
  * @description:公共dao
  */
@Mapper
public interface CommonMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/28 16:58
      * @description:公共查询方法
      * @Return:返回值List<Map<String, Object>>
      * @注意：有序
      */
    @Select("${_parameter}")
    List<LinkedHashMap<String, Object>> queryDataForSortList(String sql);


    /**
      * @author: tianyong
      * @time: 2019/7/25 16:25
      * @description:公共查询方法
      * @注意：无序
      */
    @Select("${_parameter}")
    List<Map<String, Object>> queryDataForList(String sql);


}
