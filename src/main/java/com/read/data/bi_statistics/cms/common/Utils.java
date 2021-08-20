package com.read.data.bi_statistics.cms.common;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.data.dto.DynamicDTO;
import com.read.data.bi_statistics.cms.data.dto.TreeDTO;
import com.read.data.bi_statistics.cms.entity.ReturnBone;
import com.read.data.bi_statistics.cms.service.strategy.impl.TimeSubTableStrategy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;

/**
 * @author: tianyong
 * @Time: 2019/5/20 10:22
 * @description:工具类
 */
public class Utils {

    //私有构造
    private Utils(){}


    /**
      * @author: tianyong
      * @time: 2019/5/20 20:28
      * @description:通用log
      */
    public static final Logger loggers = LoggerFactory.getLogger(Utils.class);


    /**
     * @author: tianyong
     * @time: 2019/2/19 9:19
     * @description:通用打印语句
     */
    public static void printf(Object o){
        loggers.info("<---------begin---------->");
        loggers.info(String.valueOf(o));
        loggers.info("<----------end----------->");
    }


    /**
      * @author: tianyong
      * @time: 2019/5/31 17:14
      * @description:请求参数request转换为Map<Stirng,Object>
      */
    public static Map<String,Object> getRequestParams(HttpServletRequest request){
        //参数定义
        String paraName = null;
        Map<String, Object> parameters = new HashMap<>();
        //获取请求参数并转换
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            paraName = enu.nextElement();
            parameters.put(paraName, request.getParameter(paraName));
        }
        return parameters;
    }



    /**
      * @author: tianyong
      * @time: 2019/7/9 11:10
      * @description:POST请求参数request转换为Map<Stirng,Object>
      */
    public static Map<String,Object> getPostRequestParams(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            loggers.error("POST请求参数转换错误!",e);
            throw new StatusCodeBO(CodeInfo.POST_INVALID_PARAM);
        }
        return str2Map(json.toString());
    }



    /**
     * @author: tianyong
     * @time: 2019/5/16 19:50
     * @description:组织父子结构数据
     */
    public static <T extends TreeDTO> List<T> makeTreeBones(List<T> list) {
        List<T> result = new ArrayList<>();
        list.forEach(mid->{
            if(Predicate.isEqual(mid.getParent_id()).test(0)){
                //添加一级菜单
                result.add(mid);
                //递归添加当前菜单的子菜单
                getComponentTree(mid, list);
            }
        });
        return result;
    }
    private static <T extends TreeDTO> void getComponentTree(T t, List<T> list) {
        for (T mid : list) {
            //如果menu列表id == 当前一级menu_id，则跳过
            if (mid.getId() == t.getId()) continue;
            //如果menu列表parent_id == 当前一级menu_id,则添加
            if (mid.getParent_id() == t.getId()) {
                t.getChildren().add(mid);
                //递归，继续添加
                getComponentTree(mid, list);
            }
        }
    }


    /**
      * @author: tianyong
      * @time: 2019/5/20 20:21
      * @description:将两个对象合并为一个对象(限制：两个对象)
      */
    public static Object makeDoubleObject2One(Object o, Map<String, Object> extra) {
        //获取基础属性集合
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(o);
        Map<String, Class> propertyMap = Maps.newHashMap();
        for (PropertyDescriptor d : descriptors) {
            //判断是否关键字冲突
            if (!Constants.DataType.CLASS.equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        //添加额外的属性
        extra.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        //生成动态bean对象
        DynamicDTO dynamicBean = new DynamicDTO(o.getClass(), propertyMap);
        //添加基础属性值
        propertyMap.forEach((k, v) -> {
            try {
                if (!extra.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(o, k));
                }
            } catch (Exception e) {
                loggers.error("添加基础属性值错误!", e);
                throw new StatusCodeBO(CodeInfo.ADD_BASE_ATTRIBUTE_FAIL);
            }
        });
        //添加额外属性值
        extra.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                loggers.error("添加额外属性值错误!", e);
                throw new StatusCodeBO(CodeInfo.HANDLER_RETURN_DATA_FAIL);
            }
        });
        //加工返回数据
        try {
            BeanUtils.copyProperties(dynamicBean,dynamicBean.getObject());
        } catch (Exception e) {
            loggers.error("加工返回数据错误!",e);
            throw new StatusCodeBO(CodeInfo.ADD_EXTRA_ATTRIBUTE_FAIL);
        }
        return dynamicBean.getObject();
    }


    /**
      * @author: tianyong
      * @time: 2019/6/4 11:30
      * @description:请求成功返回数据
      */
    public static ReturnBone success(Object data){
        ReturnBone rb = new ReturnBone();
        rb.setCode(100000);
        rb.setMsg("请求成功");
        rb.setData(data);
        return rb;
    }


    /**
     * @author: tianyong
     * @time: 2019/6/4 11:30
     * @description:请求失败
     */
    public static ReturnBone error(CodeInfo codeInfo){
        ReturnBone rb = new ReturnBone();
        rb.setCode(codeInfo.getCode());
        rb.setMsg(codeInfo.getMsg());
        return rb;
    }



    /**
     * @author: tianyong
     * @time: 2019/5/30 14:44
     * @description:转换周期类别
     */
    public static String transDateType(String str){
        if(str == null) return null;
        String s = null;
        switch (str){
            case "1":
                s = Constants.TimeType.DAY.getValue().toString();
                break;
            case "2":
                s = Constants.TimeType.WEEK.getValue().toString();
                break;
            case "3":
                s = Constants.TimeType.MONTH.getValue().toString();
                break;
            default:
                loggers.info("转换周期类别错误!");
                throw new StatusCodeBO(CodeInfo.TRANS_DATE_TYPE_FAIL);
        }
        return s;
    }


    /**
      * @author: tianyong
      * @time: 2019/6/19 11:17
      * @description:字符串拼接
      */
    public static String append2Str(String mark,List list){
        StringJoiner sj=new StringJoiner(mark);
        for(Object o:list){
            sj.add((CharSequence) o);
        }
        return sj.toString();
    }


    /**
      * @author: tianyong
      * @time: 2019/7/1 14:21
      * @description:date->localdate
      */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }


    /**
      * @author: tianyong
      * @time: 2019/7/15 11:30
      * @description:计算日期的偏移量
      */
    public static Date countDate(Date date,int num){
        return DateUtils.addDays(date,num);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/8 13:51
      * @description:String->List<Map>
      */
    public static List<Map<String,Object>> str2ListMap(String str) {
        List<Map<String,Object>> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str, new TypeToken<List<Map<String, Object>>>() {}.getType());
        } catch (JsonSyntaxException e) {
            loggers.error("String->List<Map>转换错误!", e);
            throw new StatusCodeBO(CodeInfo.TRANS_DATA_TYPE_FAIL);
        }
        return res;
    }



    /**
      * @author: tianyong
      * @time: 2019/7/8 13:51
      * @description:String->Map
      */
    public static Map<String,Object> str2Map(String str) {
        Map<String,Object> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str, new TypeToken<Map<String, Object>>() {}.getType());
        } catch (JsonSyntaxException e) {
            loggers.error("String->Map转换错误!", e);
            throw new StatusCodeBO(CodeInfo.TRANS_DATA_TYPE_FAIL);
        }
        return res;
    }


    /**
     * @author: tianyong
     * @time: 2019/6/18 18:51
     * @description:反射调用方法，获取return值
     */
    public static String reflectInvokeMethod(Class<?> clazz,String mn,Map params){
        String res = "";
        try {
            Method method = clazz.getMethod(mn,Map.class);
            method.setAccessible(true);
            res = method.invoke(clazz.newInstance(),params).toString();
        } catch (Exception e) {
            loggers.error("反射调用方法错误!", e);
            throw new StatusCodeBO(CodeInfo.REFLECT_INVOKE_METHOD_FAIL);
        }
        return res;
    }



    /**
     * @author: tianyong
     * @time: 2019/6/18 18:51
     * @description:反射调用构造函数，获取return值 (待完善)
     */
    public static Constructor<?> reflectInvokeConstructor(Class<?> clazz){
        TimeSubTableStrategy t = null;
        Constructor<?> cst = null;
        try {
            cst = clazz.getConstructor(new Class[]{String.class, Map.class});
        } catch (Exception e) {
            loggers.error("反射调用构造函数错误!", e);
            throw new StatusCodeBO(CodeInfo.REFLECT_INVOKE_CONSTRUCTOR_FAIL);
        }
        return cst;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/12 16:28
      * @description:提取字符串中的数字(含小数点)
      */
    public static String regexNumber(String str){
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        if (m.find()) {
            //如果有相匹配的,则判断是否为null操作
            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
                str = "";
            }
        }
        return str;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/12 16:29
      * @description:提取字符串中的汉字
      * @param："[^\\u4e00-\\u9fa5]"也可以
      */
    public static String regexHanZi(String str){
        //使用正则表达式
        Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
        return pattern.matcher(str).replaceAll("");
    }


    /**
      * @author: tianyong
      * @time: 2019/7/15 11:24
      * @description:获取当前日期
      */
    public static Date getCurentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(sdf.format(System.currentTimeMillis()));
        } catch (ParseException e) {
            loggers.error("获取当前日期错误!", e);
            throw new StatusCodeBO(CodeInfo.GET_CURRENT_DATE_FAIL);
        }
        return date;
    }


    /**
      * @author: tianyong
      * @time: 2019/8/5 11:23
      * @description:map转实体类
      */
    public static void map2Entity(Map<String,Object> map, Object entity) {
        try {
            BeanUtils.copyProperties(entity,map);
        } catch (Exception e) {
            loggers.error("map转换entity错误!", e);
            throw new StatusCodeBO(CodeInfo.TRANS_DATA_TYPE_FAIL);
        }
    }



    /**
      * @author: tianyong
      * @time: 2019/8/12 15:56
      * @description:比较两个日期
      */
    public static int compareDate(String var1,Date var2){
        try {
            Date first = new SimpleDateFormat("yyyy-MM").parse(var1);
            return first.compareTo(var2);
        } catch (ParseException e) {
            loggers.error("日期比较错误!", e);
            throw new StatusCodeBO(CodeInfo.TRANS_DATA_TYPE_FAIL);
        }
    }

}
