package com.read.data.bi_statistics.cms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: tianyong
 * @Time: 2019/6/4 11:27
 * @description:resultful接口返回结构
 */
@Setter
@Getter
@ToString
public class ReturnBone<T> {


    /*返回码*/
    private Integer code;

    /*提示信息 */
    private String msg;

    /*数据*/
    private  T data;


}

