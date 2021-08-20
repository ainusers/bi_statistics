package com.read.data.bi_statistics.cms.dao;

import com.read.data.bi_statistics.cms.data.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
 * @author: tianyong
 * @Time: 2018/8/20 16:09
 * @description:菜单dao
 */
@Mapper
public interface MenuMapper {


    /**
      * @author: tianyong
      * @time: 2019/5/16 17:17
      * @description:根据当前用户信息查询菜单信息
      */
    @Select("select menu.id,menu.parent_id,menu.menu_name,menu.menu_icon,menu.active,group_concat(page.id) page_id from bi_menu_info menu " +
            "LEFT JOIN " +
            "bi_page_info page " +
            "on menu.id = page.menu_id " +
            "where menu.enable = 1 and menu.is_del = 0  and menu.id in (select menu_id from bi_role_menu " +
            "where role_id in (select id from bi_user_role " +
            "where user_id in (select id from bi_user_info where username = #{username} )))" +
            "GROUP BY menu.id")
    List<MenuDTO> getMenusByUserName(String username);


}
