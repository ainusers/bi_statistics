package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import static com.read.data.bi_statistics.cms.pattern.context.FormatReturnDataFactory.FormatReturnDataFactory;


/**
  * @author: tianyong
  * @time: 2019/7/5 13:58
  * @description:Radar雷达图选项
  */
@Service
public class OptionRadarServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/5 13:58
      * @description:Radar雷达图数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        Map res = new HashMap();
        res.put("type","control_chart_radar");
        res.put("source",FormatReturnDataFactory(FormatRadarReturnDataServiceImpl.class, o));
        return res;
    }

}
