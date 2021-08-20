package com.read.data.bi_statistics.cms.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/5/21 15:16
 * @description:
 */
@Setter
@Getter
@ToString
public class PageAndComponentDTO implements Serializable {


    private Integer id;
    @JsonProperty("name")
    private String page_name;
    private List<LogicComponentDTO> component = new ArrayList<>();


}
