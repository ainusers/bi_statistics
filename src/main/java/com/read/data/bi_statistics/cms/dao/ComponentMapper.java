package com.read.data.bi_statistics.cms.dao;

import com.read.data.bi_statistics.cms.data.dto.LogicComponentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
  * @author: tianyong
  * @time: 2019/5/17 16:27
  * @description:组件dao
  */
@Mapper
public interface ComponentMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/17 16:27
      * @description:查询组件信息
      */
    @Select("select id,parent_id,type,layout,scope,description from bi_component_info " +
            "where page_id = #{pageId} and enable = 1 and is_del = 0 " +
            "order by sort")
    List<LogicComponentDTO> getLogicComponentsByPageId(String pageId);

}
