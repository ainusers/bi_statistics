package com.read.data.bi_statistics.cms.data.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * @author: tianyong
 * @Time: 2019/6/20 14:06
 * @description: 表格参数对象  //待完善备注信息，需纳入文档
 */
@Setter
@Getter
@ToString
public class TableObjBO implements Serializable {


    //表 变量名
    private String tableVariableName;
    //表名片段参数 (需与数据库字段相同)
    private String tableKey;
    //表名 (1)如果未分表，tablename代表表名 (2)如果分表，代表基础表名(表名片段)
    private String tableName;
    //分表标记
    private String flag;
    //分表策略 (需与分表策略实现类名相同)
    private String policy;
    //分表参数 (需与数据库字段相同)
    private String subTableParam;


}
