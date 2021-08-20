package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
  * @author: tianyong
  * @time: 2019/7/10 16:39
  * @description:lineAreaPercent面积百分比选项
  */
@Service
public class OptionLineAreaPercentServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/10 16:39
      * @description:lineAreaPercent面积百分比数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        List lists = (List) o;
        Map res = new HashMap();
        res.put("type","control_chart_percent");
        res.put("source",(List<LinkedHashMap<String, Object>>) lists.get(1));
        return res;
    }

}
