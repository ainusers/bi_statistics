package com.read.data.bi_statistics.cms.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/5/17 19:24
 * @description:父子结构实体DTO
 */
@Setter
@Getter
@ToString
public abstract class TreeDTO<E> implements Serializable {


    private Integer id;
    @JsonIgnore
    private Integer parent_id;
    private List<E> children = new ArrayList();


}
