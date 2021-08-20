package com.read.data.bi_statistics.cms.dao;

import com.read.data.bi_statistics.cms.data.dto.ControlDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
  * @author: tianyong
  * @time: 2019/5/21 19:34
  * @description:控件dao
  */
@Mapper
public interface ControlMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/21 19:34
      * @description:查询当前页面的控件数据(通过组件关联)
      */
    @Select("select a.id,a.field,b.control_scope,b.component_id,a.label,a.type,a.description,a.default_value,a.data_source from " +
            "(select id,field,label,type,description,default_value,data_source from bi_control_info " +
            "where id in ( " +
            "select control_id from bi_component_control " +
            "where component_id in ( " +
            "select id from bi_component_info " +
            "where parent_id != 0 and page_id = #{pageId} " +
            ") " +
            ")) a " +
            "left JOIN " +
            "(select control_id,sort,component_id,control_scope from bi_component_control " +
            "where component_id in ( " +
            "select id from bi_component_info " +
            "where parent_id != 0 and page_id = #{pageId} " +
            ")) b " +
            "on a.id = b.control_id ORDER BY b.sort asc")
    List<ControlDTO> getCurrentControl(String pageId);

}
