package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.FormatReturnDataService;
import java.util.*;

/**
 * @author: tianyong
 * @Time: 2019/7/26 15:24
 * @description:Table格式化数据服务
 */
public class FormatTableReturnDataServiceImpl implements FormatReturnDataService {



    /**
      * @author: tianyong
      * @time: 2019/7/26 15:25
      * @description:获取格式化返回数据
      */
    public Object FormatReturnData(Object o){
        List lists = (List) o;
        List<Object> lo = (List<Object>) lists.get(0);
        List<LinkedHashMap<String, Object>> llhm = (List<LinkedHashMap<String, Object>>) lists.get(1);
        List<Object> data = new ArrayList<>();
        data.add(lo);
        //填充表格数据
        for(int i=0,len = llhm.size();i<len;i++){
            data.add(llhm.get(i).values());
        }
        return data;
    }

}
