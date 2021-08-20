package com.read.data.bi_statistics.cms.pattern.context;

import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.service.OptionService;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
 * @author: tianyong
 * @Time: 2019/6/26 11:17
 * @description:格式化工厂(主要用于选择格式化返回数据)
 */
public class FormatReturnStructureFactory {
    private FormatReturnStructureFactory(){}


    /**
      * @author: tianyong
      * @time: 2019/6/27 10:32
      * @description:返回当前接口 类对象
      */
    public static <T extends OptionService> T getInstance(String className){
        T os = null;
        try {
            os = (T)Class.forName(className).newInstance();
        }catch (Exception e) {
            loggers.error("返回当前接口类对象错误!", e);
            throw new StatusCodeBO(CodeInfo.RETURN_TARGET_FAIL);
        }
        return os;
    }

}
