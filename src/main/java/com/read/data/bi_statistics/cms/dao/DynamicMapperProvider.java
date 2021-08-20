package com.read.data.bi_statistics.cms.dao;

import java.util.Map;


/**
 * @author: tianyong
 * @Time: 2019/7/17 14:35
 * @description:动态MapperProvider
 */
public class DynamicMapperProvider {


    /**
     * @author: tianyong
     * @time: 2019/7/16 18:08
     * @description:查询当前所有版本
     */
    public String getCurrentVersion(Map params){
        Map dt = (Map) params.get("dt");
        return "select version from version where 1=1 and apptype = '"+params.get("apptype")+"' and is_new = '"+params.get("is_new")+"' and channel = '"+params.get("channel")+"' and dt >= '"+dt.get("start").toString().replace("-","")+"' AND dt <= '"+dt.get("end").toString().replace("-","")+"' GROUP BY version";
    }


}
