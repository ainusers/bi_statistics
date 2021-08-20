package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.*;


/**
  * @author: tianyong
  * @time: 2019/7/3 15:12
  * @description:Bar柱状图选项
  */
@Service
public class OptionBarServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/3 15:11
      * @description:Bar柱状图数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        List lists = (List) o;
        Map res = new HashMap();
        res.put("type","control_chart_bar");
        res.put("source",(List<LinkedHashMap<String, Object>>) lists.get(1));
        return res;
    }

}
