package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.FormatReturnDataService;
import java.util.*;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.MAX_DAY;

/**
 * @author: tianyong
 * @Time: 2019/7/26 15:24
 * @description:复杂lineArea面积图格式化数据服务
 */
public class FormatComplexLineAreaReturnDataServiceImpl implements FormatReturnDataService {



    /**
      * @author: tianyong
      * @time: 2019/7/26 15:25
      * @description:获取格式化返回数据
      */
    public Object FormatReturnData(Object o){
        List lists = (List) o;
        List<LinkedHashMap<String, Object>> datas = (List<LinkedHashMap<String, Object>>) lists.get(1);
        for(int i=0,len=datas.size()-1;i<len;i++){
            //只允许查看的天数
            if(i>MAX_DAY){
                datas.removeAll(datas.subList(i,datas.size()));
                break;
            }
            for(int j=datas.get(i).size()-1;j>i+1;j--){
                datas.get(i).put(j + "天后",0);
            }
        }
        return datas;
    }

}
