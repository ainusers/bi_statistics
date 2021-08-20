package com.read.data.bi_statistics.cms.data.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * @author: tianyong
 * @Time: 2019/6/20 14:01
 * @description: sql模板BO
 */
@Setter
@Getter
@ToString
public class SqlBO implements Serializable {


    //sql模板中 sql语句
    private String sql;
    //sql模板中 sql字段变量
    private List<String> fieldVariable;
    //sql模板中 sql表名变量
    private List<String> tablenameVariable;
    //sql模板中 sql条件变量
    private List<String> filtersVariable;
    //sql模板中 <table>表格标签对象
    private List<TableObjBO> tableObj;


    public String getSqlTemplate() {
        return sql;
    }

    public void setSqlTemplate(String sqlTemplate) {
        this.sql = sqlTemplate;
    }


}
