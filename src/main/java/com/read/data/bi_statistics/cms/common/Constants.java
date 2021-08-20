package com.read.data.bi_statistics.cms.common;

/**
 * @author: tianyong
 * @Time: 2019/5/20 19:16
 * @description:公共常量库
 */
public class Constants {
    //私有构造
    private Constants(){}


    /**
      * @author: tianyong
      * @time: 2019/5/20 20:36
      * @description:基本数据类型常量类
      */
    public static class DataType{
        private DataType(){}

        //关键字
        public static final String CLASS = "class";
        //组件名称
        public static final String COMPONENT = "component";
        //字段集合
        public static final String PAGE_ID = "page_id";
        //api接口访问前缀
        public static final String URI = "http://10.22.1.240:9032";
        //增长_留存面积图，(天数)个数
        public static final int DAY = 31;
        //增长_留存面积图，最大允许天数
        public static final int MAX_DAY = 29;
        //jwt工具类中：用户名保存key
        public static final String TOKEN_USERNAME = "username";
        //设置过期时间 :5分钟
        public static final long EXPIRE_TIME = 30*60*1000;
    }


    /**
      * @author: tianyong
      * @time: 2019/5/30 15:04
      * @description:时间类别
      */
    public enum TimeType{
        //转换周期类别
        DAY("day"),WEEK("week"),MONTH("month");
        private final Object value;
        public Object getValue() {
            return value;
        }
        private TimeType(Object value){
            this.value = value;
        }
    }


}
