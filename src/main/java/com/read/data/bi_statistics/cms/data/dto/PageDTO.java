package com.read.data.bi_statistics.cms.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @author: tianyong
 * @Time: 2019/5/21 10:25
 * @description:页面DTO
 */
@Setter
@Getter
public class PageDTO implements Serializable {


    private Integer id;
    @JsonProperty("name")
    private String page_name;

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", page_name:'" + page_name + '\'' +
                '}';
    }
}
