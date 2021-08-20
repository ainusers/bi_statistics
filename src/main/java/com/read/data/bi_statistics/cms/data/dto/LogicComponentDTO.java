package com.read.data.bi_statistics.cms.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/5/17 11:06
 * @description:逻辑组件DTO
 */
@Setter
@Getter
@ToString
public class LogicComponentDTO extends TreeDTO<LogicComponentDTO> {


    @JsonIgnore
    private String type;
    @JsonIgnore
    private String layout;
    @JsonIgnore
    private String scope;
    private String description;
    @JsonProperty("scope")
    private List<Integer> scopes = new ArrayList();
    @JsonProperty("layout")
    private Object layouts;

    //控件DTO
    private List<ControlDTO> control = new ArrayList<>();


}
