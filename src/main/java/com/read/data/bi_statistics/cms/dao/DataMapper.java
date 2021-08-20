package com.read.data.bi_statistics.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import java.util.List;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/7/16 18:07
 * @description:数据dao
 */
@Mapper
public interface DataMapper {


    /**
     * @author: tianyong
     * @time: 2019/7/16 18:08
     * @description:查询当前所有版本
     */
    @SelectProvider(type = DynamicMapperProvider.class,method = "getCurrentVersion")
    List<String> getCurrentVersion(Map params);


    /**
      * @author: tianyong
      * @time: 2019/7/29 15:38
      * @description:查询当前所有渠道
      */
    @Select("select qumc from meta_channels GROUP BY qumc")
    List<String> getAllChannels();


    /**
      * @author: tianyong
      * @time: 2019/7/29 15:38
      * @description:查询当前所有页面
      */
    @Select("select event_name from meta_events where event_type = 'page'")
    List<String> getAllPages();


    /**
     * @author: tianyong
     * @time: 2019/7/29 15:38
     * @description:查询当前所有事件
     */
    @Select("select event_name from meta_events where event_type = 'click'")
    List<String> getAllEvents();


}
