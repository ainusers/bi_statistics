package com.read.data.bi_statistics.cms.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/5/17 14:37
 * @description:控件DTO
 */
@Setter
@Getter
@ToString
public class ControlDTO implements Serializable {


    private Integer id;
    @JsonIgnore
    private Integer component_id;
    private String field;
    @JsonIgnore
    private String control_scope;
    @JsonIgnore
    private String label;
    private String type;
    private String description;
    @JsonIgnore
    private String default_value;
    @JsonIgnore
    private String data_source;
    @JsonProperty("default_value")
    private Object default_values;
    @JsonProperty("data_source")
    private Object data_sources;
    @JsonProperty("control_scope")
    private List scopes = new ArrayList();


}
