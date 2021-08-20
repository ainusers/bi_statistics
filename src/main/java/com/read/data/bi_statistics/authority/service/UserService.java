package com.read.data.bi_statistics.authority.service;

import com.read.data.bi_statistics.authority.entity.UserInfo;

/**
 * @author: tianyong
 * @Time: 2019/9/10 13:46
 * @description:用户服务
 */
public interface UserService {


    /**
      * @author: tianyong
      * @time: 2019/9/10 13:46
      * @description:根据用户名查询用户信息
      */
    UserInfo queryUserInfoByUsername(String username);


    /**
     * @author: tianyong
     * @time: 2019/9/10 14:50
     * @description:修改用户密码
     */
    void updateUserPassword(String username,String password,String change_password);


}
