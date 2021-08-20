package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.FormatReturnDataService;
import java.util.*;
import static com.read.data.bi_statistics.cms.common.Utils.regexHanZi;
import static com.read.data.bi_statistics.cms.common.Utils.regexNumber;

/**
 * @author: tianyong
 * @Time: 2019/7/26 15:24
 * @description:Radar格式化数据服务
 */
public class FormatRadarReturnDataServiceImpl implements FormatReturnDataService {



    /**
      * @author: tianyong
      * @time: 2019/7/26 15:25
      * @description:获取格式化返回数据
      */
    public Object FormatReturnData(Object o){
        List lists = (List) o;
        List<LinkedHashMap<String, Object>> lhm = (List<LinkedHashMap<String, Object>>) lists.get(1);
        for(LinkedHashMap<String, Object> obj:lhm){
            String value = obj.get("value").toString();
            if(value.indexOf(",") != -1){
                String[] datas = value.split(",");
                for(String s:datas){
                    obj.put(regexHanZi(s),Double.valueOf(regexNumber(s)));
                }
            }
            obj.remove("value");
        }
        return lhm;
    }

}
