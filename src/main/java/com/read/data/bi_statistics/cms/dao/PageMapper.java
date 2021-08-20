package com.read.data.bi_statistics.cms.dao;

import com.read.data.bi_statistics.cms.data.dto.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author: tianyong
 * @Time: 2018/8/20 16:09
 * @description:页面dao
 */
@Mapper
public interface PageMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/21 16:01
      * @description:查询当前页面数据
      */
    @Select("select id,page_name from bi_page_info " +
            "where is_del = 0 and id = #{pageId}")
    PageDTO getCurrentPage(String pageId);

}
