package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.*;


/**
  * @author: tianyong
  * @time: 2019/7/3 15:12
  * @description:MultipleLine折线图选项
  */
@Service
public class OptionMultipleLineServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/3 15:11
      * @description:MultipleLine折线图数据格式化
      */
    static int count=0;
    static List<LinkedHashMap<String, Object>> datas = new ArrayList();
    public Object formatAlgorithm(Object o) {
        List lists = (List) o;
        List<LinkedHashMap<String, Object>> colls = (List<LinkedHashMap<String, Object>>) lists.get(1);
        if(datas.size() == 0){
            datas.addAll(colls);
        }else{
            for(int i=0;i<datas.size();i++){
               datas.get(i).put(""+count+"",colls.get(i).get("retention_per"));
            }
            count++;
        }
        Map res = new HashMap();
        res.put("type","control_chart_line");
        res.put("source",datas);
        return res;
    }

}
