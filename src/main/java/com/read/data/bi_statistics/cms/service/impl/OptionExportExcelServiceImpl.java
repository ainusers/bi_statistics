package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.service.OptionService;
import org.springframework.stereotype.Service;
import static com.read.data.bi_statistics.cms.pattern.context.FormatReturnDataFactory.FormatReturnDataFactory;


/**
  * @author: tianyong
  * @time: 2019/8/5 14:04
  * @description:export_exccel选项
  */
@Service
public class OptionExportExcelServiceImpl implements OptionService {


    /**
      * @author: tianyong
      * @time: 2019/8/5 14:05
      * @description: export_excel数据格式化
      */
    @Override
    public Object formatAlgorithm(Object o) {
        return FormatReturnDataFactory(FormatExportExcelReturnDataServiceImpl.class, o);
    }

}
