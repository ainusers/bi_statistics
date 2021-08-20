package com.read.data.bi_statistics.cms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: tianyong
 * @Time: 2019/5/16 17:04
 * @description:菜单基础信息
 */
@Setter
@Getter
@ToString
public class MenuInfo implements Serializable {


    private Integer id;
    private Integer parent_id;
    private String menu_name;
    private String menu_icon;
    private Integer sort;
    private Integer active;
    private Integer enable;
    private Integer is_del;
    private Date createtime;
    private Date updatetime;
    private String createuser;
    private String updateuser;


}
