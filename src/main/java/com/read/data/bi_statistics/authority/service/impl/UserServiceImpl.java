package com.read.data.bi_statistics.authority.service.impl;

import com.read.data.bi_statistics.authority.dao.UserMapper;
import com.read.data.bi_statistics.authority.entity.UserInfo;
import com.read.data.bi_statistics.authority.service.UserService;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/9/10 13:51
 * @description:用户服务
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * @author: tianyong
     * @time: 2019/9/10 13:46
     * @description:根据用户名查询用户信息
     */
    @Override
    public UserInfo queryUserInfoByUsername(String username) {
        return userMapper.queryUserInfoByUsername(username);
    }


    /**
     * @author: tianyong
     * @time: 2019/9/10 14:50
     * @description:修改用户密码
     */
    public void updateUserPassword(String username,String password,String change_password){
        //检验是否为用户本人
        UserInfo userInfo = userMapper.queryUserInfoByUsername(username);
        if(!password.equals(userInfo.getPassword())) {
            loggers.error("原密码输入错误，可能非用户本人操作!");
            throw new StatusCodeBO(CodeInfo.ACCESS_IS_USERINFO);
        }
        //进行修改操作
        userMapper.updateUserPassword(username,change_password);
    }

}
