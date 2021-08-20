package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.*;
import static com.read.data.bi_statistics.cms.pattern.context.FormatReturnDataFactory.FormatReturnDataFactory;


/**
  * @author: tianyong
  * @time: 2019/7/3 15:12
  * @description:复杂lineArea面积图选项
  */
@Service
public class OptionComplexLineAreaServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/3 15:11
      * @description:复杂lineArea面积图数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        Map res = new HashMap();
        res.put("type","control_chart_area");
        res.put("source",FormatReturnDataFactory(FormatComplexLineAreaReturnDataServiceImpl.class, o));
        return res;
    }

}
