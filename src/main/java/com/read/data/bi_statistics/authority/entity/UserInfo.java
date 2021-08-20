package com.read.data.bi_statistics.authority.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: tianyong
 * @Time: 2019/8/14 17:55
 * @description:用户信息entity
 */
@Setter
@Getter
@ToString
public class UserInfo implements Serializable {


    private Integer id;
    private String username;
    private String label;
    private String password;
    private String email;
    private String salt;
    private Integer status;
    private Integer is_del;
    private Date createtime;
    private Date updatetime;
    private String createuser;
    private String updateuser;


}
