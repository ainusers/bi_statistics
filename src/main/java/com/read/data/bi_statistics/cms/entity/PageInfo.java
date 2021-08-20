package com.read.data.bi_statistics.cms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: tianyong
 * @Time: 2019/5/16 18:06
 * @description:页面基础信息
 */
@Setter
@Getter
@ToString
public class PageInfo implements Serializable {


    private Integer id;
    private Integer menu_id;
    private String page_name;
    private Integer is_del;
    private String description;
    private Date createtime;
    private Date updatetime;
    private String createuser;
    private String updateuser;


}
