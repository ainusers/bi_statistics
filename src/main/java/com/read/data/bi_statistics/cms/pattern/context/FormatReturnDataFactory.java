package com.read.data.bi_statistics.cms.pattern.context;

import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.service.FormatReturnDataService;
import java.lang.reflect.Method;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;

/**
  * @author: tianyong
  * @time: 2019/7/26 16:04
  * @description:格式化返回数据工厂
  */
public class FormatReturnDataFactory {
    private FormatReturnDataFactory(){}


    /**
      * @author: tianyong
      * @time: 2019/7/26 16:04
      * @description:格式化返回数据方法
      */
    public static <T extends FormatReturnDataService> Object FormatReturnDataFactory(Class clazz, Object object){
        Object result = null;
        try {
            T os = (T)clazz.newInstance();
            Method method = clazz.getDeclaredMethod("FormatReturnData", Object.class);
            result = method.invoke(os, object);
        }catch (Exception e) {
            loggers.error("格式化返回数据错误!", e);
            throw new StatusCodeBO(CodeInfo.RETURN_TARGET_FAIL);
        }
        return result;
    }

}
