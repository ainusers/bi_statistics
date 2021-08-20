package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
  * @author: tianyong
  * @time: 2019/7/3 15:12
  * @description:line折线图选项
  */
@Service
public class OptionLineServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/3 15:11
      * @description:line折线图数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        List lists = (List) o;
        Map res = new HashMap();
        res.put("type","control_chart_line");
        res.put("source",(List<LinkedHashMap<String, Object>>) lists.get(1));
        return res;
    }

}
