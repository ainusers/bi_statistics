package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import java.util.*;
import static com.read.data.bi_statistics.cms.pattern.context.FormatReturnDataFactory.FormatReturnDataFactory;


/**
  * @author: tianyong
  * @time: 2019/6/27 10:33
  * @description:table选项
  */
@Service
public class OptionTableServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/7/2 15:36
      * @description: table数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        Map res = new HashMap();
        res.put("type","control_table");
        res.put("source",FormatReturnDataFactory(FormatTableReturnDataServiceImpl.class, o));
        return res;
    }

}
