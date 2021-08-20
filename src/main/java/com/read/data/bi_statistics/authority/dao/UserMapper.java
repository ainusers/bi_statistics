package com.read.data.bi_statistics.authority.dao;

import com.read.data.bi_statistics.authority.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author: tianyong
 * @Time: 2019/8/14 18:03
 * @description:用户信息操作
 */
@Mapper
public interface UserMapper {


    /**
      * @author: tianyong
      * @time: 2019/8/14 18:04
      * @description:根据用户名查询用户信息
      */
    @Select("select username,label,password,email,status,is_del from bi_user_info where username = #{username}")
    UserInfo queryUserInfoByUsername(String username);


    /**
      * @author: tianyong
      * @time: 2019/9/10 14:50
      * @description:修改用户密码
      */
    @Update("update bi_user_info set password = #{password} where username = #{username}")
    void updateUserPassword(@Param("username") String username,@Param("password") String password);


}
