package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
  * @author: tianyong
  * @time: 2019/8/2 17:51
  * @description:HorizontalBar柱状图选项
  */
@Service
public class OptionHorizontalBarServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/8/2 17:51
      * @description:HorizontalBar柱状图数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        List lists = (List) o;
        Map res = new HashMap();
        res.put("type","control_chart_horizontal_bar");
        res.put("source",(List<LinkedHashMap<String, Object>>) lists.get(1));
        return res;
    }

}
