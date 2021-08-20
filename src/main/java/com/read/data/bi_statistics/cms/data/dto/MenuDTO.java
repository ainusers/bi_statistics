package com.read.data.bi_statistics.cms.data.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: tianyong
 * @Time: 2019/5/16 17:49
 * @description:菜单DTO
 */
@Setter
@Getter
@ToString
public class MenuDTO extends TreeDTO<LogicComponentDTO> {


    private String menu_name;
    private String menu_icon;
    private Integer active;
    private String page_id;


}
