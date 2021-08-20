package com.read.data.bi_statistics.cms.handle;

import com.read.data.bi_statistics.cms.data.bo.SqlBO;
import com.read.data.bi_statistics.cms.data.bo.TableObjBO;
import com.read.data.bi_statistics.cms.pattern.context.ExplainContext;
import com.read.data.bi_statistics.cms.pattern.context.SubTableContext;
import com.read.data.bi_statistics.cms.service.strategy.impl.DynamicTableStrategy;
import com.read.data.bi_statistics.cms.service.strategy.impl.RegexExplainStrategy;
import com.read.data.bi_statistics.cms.service.strategy.impl.TimeSubTableStrategy;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.read.data.bi_statistics.cms.common.Utils.*;

/**
 * @author: tianyong
 * @Time: 2019/5/30 10:45
 * @description:数据处理服务
 */
public class ChartDataHandle {
    //私有构造
    private ChartDataHandle(){}


    /**
      * @author: tianyong
      * @time: 2019/6/18 15:58
      * @description:正则提取字符串中子字符串
      * @param 0:包含起始和结束,1:不包含起始和结束
      */
    public  static <T extends Object> List<T> regexGetStr(T str,T begin_symbol,T end_symbol,int type){
        String rex = "\\"+begin_symbol+"(.*?)\\"+end_symbol+"";
        Pattern pp = Pattern.compile(rex);
        Matcher mm = pp.matcher((CharSequence) str);
        List res = new ArrayList();
        while(mm.find()) {
            switch (type){
                case 0:
                    res.add(mm.group(0));
                    break;
                case 1:
                    res.add(mm.group(1));
                    break;
                case 2:
                    res.add(mm.group(0) + "," + mm.group(1));
                    break;
            }
        }
        return res;
    }


    /**
     * @author: tianyong
     * @time: 2019/6/19 11:56
     * @description:解析组装条件
     */
    public static String explainFilter(String filters,Map params){
        String[] strs = filters.split(",");
        String date = null;
        String begin_date = null;
        String end_date = null;
        StringBuilder sb = new StringBuilder();
        Map dt = (Map) params.get("dt");
        for(String s:strs){
            //如果前端没有传递必要参数，则跳过条件拼接
            if(s.indexOf("dt") != -1){
                //区分时间点和时间段
                if((dt.containsKey("end"))){
                    begin_date = dt.get("start").toString().replace("-","");
                    end_date = dt.get("end").toString().replace("-","");
                    sb.append(" and ").append(s).append(" >= '").append(begin_date).append("'");
                    sb.append(" and ").append(s).append(" <= '").append(end_date).append("'");
                }else{
                    date = dt.get("start").toString().replace("-","").trim();
                    sb.append(" and ").append(s).append(" = '").append(date).append("'");
                }
            }else if(s.equals("bid")){
                if(params.get(s).toString().indexOf(",") != -1){
                    String[] datas = params.get(s).toString().split(",");
                    sb = sb.append(" and ").append(s).append(" in (");
                    for(int i=0;i<datas.length;i++){
                        if(i==0){
                            sb.append("'").append(datas[i]).append("'");
                        }else{
                            sb.append(",'").append(datas[i]).append("'");
                        }
                    }
                    sb.append(")");
                }else{
                    sb.append(" and ").append(s).append(" in ('").append(params.get(s)).append("')");
                }
            }else{
                if(params.get(s) == null || "".equals(params.get(s))) continue;
                sb.append(" and ").append(s).append(" = '").append(params.get(s)).append("'");
            }
        }
        return sb.toString();
    }


    /**
     * @author: tianyong
     * @time: 2019/6/26 19:50
     * @description:获取sql集合
     */
    public static List<SqlBO> getSqlTemplate(String sqlTemplate,Map params){
        //解析sql模板->SqlBO
        List<SqlBO> stbo = ExplainContext.getInstance(new RegexExplainStrategy(sqlTemplate,params)).strategy();
        List<SqlBO> res = null;
        //赋值SqlBO(SqlBO,params)
        res = assignValue(stbo, params);
        return res;
    }


    /**
      * @author: tianyong
      * @time: 2019/6/20 15:30
      * @description: sql模板解析算法
      */
    public static List<SqlBO> explainSqlTemplate(String sqlTemplate, Map params){
        //提取
            //提取sql片段
            List<String> sqls = regexGetStr(sqlTemplate,"<sql>","</sql>",0);
            List<SqlBO> results = new ArrayList<>();
            SqlBO stbo = null;
            for(int i=0,len=sqls.size();i<len;i++){
                String n = sqls.get(i);
                stbo = new SqlBO();
                //提取sql语句 (0:表示提取数据且包含起始和结束符号,1表示单纯提取的数据)
                String sqlParts = null;
                //区分是否需要分表
                if(n.indexOf("<table") == -1){
                    sqlParts = regexGetStr(n, "<sql>", "</sql>", 1).get(0);
                }else{
                    sqlParts = regexGetStr(n, "<sql>", "<table", 1).get(0);
                }
                stbo.setSqlTemplate(sqlParts);
                //提取字段变量
                List<String> fields_variable = null;
                if(n.indexOf("{field:") != -1){
                    fields_variable = regexGetStr(n, "{field:", "}", 0);
                }
                stbo.setFieldVariable(fields_variable);
                //提取条件变量
                List<String> filters_variable = null;
                if(n.indexOf("{filters:") != -1){
                    filters_variable = regexGetStr(n, "{filters:", "}", 0);
                }
                stbo.setFiltersVariable(filters_variable);
                //提取表名变量
                List<String> tables_variable = null;
                if(n.indexOf("{table:") != -1){
                    tables_variable = regexGetStr(n, "{table:", "}", 0);
                }
                stbo.setTablenameVariable(tables_variable);
                //提取table对象
                if(n.indexOf("<table") != -1){
                    List<String> tableObj = regexGetStr(n, "<table", "</table>", 0);
                    for(int j=0,len2=tableObj.size();j<len2;j++){
                        TableObjBO tobo = new TableObjBO();
                        List<TableObjBO> res = new ArrayList<>();
                        String toj = tableObj.get(j).toString();
                        //提取变量表名
                        String nameVariable = regexGetStr(toj, "{name=", "}", 1).get(0);
                        tobo.setTableVariableName(nameVariable);
                        //提取片段参数
                        String partParamVariable = regexGetStr(toj, "{partParam=", "}", 1).get(0);
                        tobo.setTableKey(partParamVariable);
                        //根据参数中参数类型选择对应基础表
                        //区分是否需要分表
                        String baseTablePart = null;
                        String baseTable = null;
                        String flag = null;
                        String policy = null;
                        String param = null;
                        String baseTableType = "";
                        //如果为空，则默认类别为day
                        if(params.get("date_type") == null){
                            baseTableType = "day";
                        }else{
                            baseTableType = transDateType(params.get("date_type").toString());
                        }
                        baseTablePart = regexGetStr(toj, ">"+baseTableType+":", ";", 0).get(0);
                        if(toj.indexOf("union") != -1 || toj.indexOf("dynamic") != -1){
                            //提取基础表名
                            baseTable = regexGetStr(baseTablePart, ">"+baseTableType+":", ":", 1).get(0);
                            tobo.setTableName(baseTable);
                            //提取分表标志
                            flag = regexGetStr(baseTablePart, "[flag=", ",", 1).get(0);
                            tobo.setFlag(flag);
                            //提取分表策略
                            policy = regexGetStr(baseTablePart, ",policy=", ",", 1).get(0);
                            tobo.setPolicy(policy);
                            //提取分表参数
                            param = regexGetStr(baseTablePart, ",param=", "]", 1).get(0);
                        }else{
                            //提取基础表名
                            baseTable = regexGetStr(baseTablePart, ">"+baseTableType+":", ";", 1).get(0);
                            tobo.setTableName(baseTable);
                        }
                        tobo.setSubTableParam(param);
                        //添加table对象
                        res.add(tobo);
                        stbo.setTableObj(res);
                    }
                }
                //添加结果集对象
                results.add(stbo);
            }
        return results;
    }


    /**
      * @author: tianyong
      * @time: 2019/6/20 16:16
      * @description: sql赋值算法
      */
    public static List<SqlBO> assignValue(List<SqlBO> stbo,Map params){
        //组装条件 list
        List<SqlBO> lstbo = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0,len=stbo.size();i<len;i++){
            SqlBO sbo = stbo.get(i);
            String sql = sbo.getSqlTemplate();
            //赋值(包含获取值)
                //赋值 field (reflect)
                if(sbo.getFieldVariable() != null){
                    for(int a=0,len2=sbo.getFieldVariable().size();a<len2;a++){
                        String method = regexGetStr(sbo.getFieldVariable().get(a), "{field:\\$", "}", 1).get(0).toString();
                        String field = reflectInvokeMethod(ChartHandle.class, method,params);
                        sql = sql.replace(sbo.getFieldVariable().get(a),field);
                    }
                }
                //赋值 条件 (explainFilter、reflect)
                if(sbo.getFiltersVariable() != null){
                    for(int b=0,len3=sbo.getFiltersVariable().size();b<len3;b++){
                        if(sbo.getFiltersVariable().toString().indexOf("$") != -1){
                            String filterMethod = regexGetStr(sbo.getFiltersVariable().get(b), "{filters:\\$", "}", 1).get(0).toString();
                            String filter = reflectInvokeMethod(ChartHandle.class, filterMethod,params);
                            sql = sql.replace(sbo.getFiltersVariable().get(b), filter);
                        }else{
                            String filters = regexGetStr(sbo.getFiltersVariable().get(b), "{filters:", "}", 1).get(0).toString();
                            String filter = explainFilter(filters, params);
                            sql = sql.replace(sbo.getFiltersVariable().get(b), filter);
                        }
                    }
                }
                // -- 赋值 tablename (二选一)
                //(分表标记判断是否需要分表)
                if(sbo.getTableObj() != null){
                    for(int c=0,len4=sbo.getTableObj().size();c<len4;c++){
                        if(StringUtils.equals("union",sbo.getTableObj().get(c).getFlag())){
                            //调用策略参数、结合分表参数获取表名集合
                            List<String> ls = SubTableContext.getInstance(new TimeSubTableStrategy(sbo.getTableObj().get(c).getTableName(), params)).strategy();
                            //遍历获取到的表名集合
                            String mid = sql;
                            for(int j = 0,len2 = ls.size();j<len2;j++){
                                sb.append(" union ");
                                //未替换表名的sql、配合union操作分表
                                sql = mid.replace(sbo.getTablenameVariable().get(c),ls.get(j));
                                sb.append(" ( " + sql + " ) ");
                                sql = sb.substring(6).toString();
                            }
                        } else if(StringUtils.equals("dynamic",sbo.getTableObj().get(c).getFlag())){
                            //调用策略参数、结合分表参数获取表名集合
                            List<String> ls = SubTableContext.getInstance(new DynamicTableStrategy(sbo.getTableObj().get(c).getTableName(), params)).strategy();
                            //遍历获取到的表名集合
                            String mid = sql;
                            for(int j = 0,len2 = ls.size();j<len2;j++){
                                //未替换表名的sql、配合union操作分表
                                sql = mid.replace(sbo.getTablenameVariable().get(c),ls.get(j));
                            }
                        } else{
                            //赋值表名
                            sql = sql.replace(sbo.getTablenameVariable().get(c),sbo.getTableObj().get(c).getTableName());
                        }
                    }
                }
            sbo.setSqlTemplate(sql);
            //待测试 截取/逻辑分支if
            lstbo.add(sbo);
        }
        return lstbo;
    }


    /**
     * @author: tianyong
     * @time: 2019/6/19 11:09
     * @description:策略组装分表名称集合
     */
    public static List<String> makeSubTableLists(String tablename,Map<String,Object> params) {
        //根据表名和时间参数，获取表名集合
        List<String> res = new ArrayList<>();
        //计算时间差
        Map date = (Map) params.get("dt");
        //判断是否为增长留存数据(存在数据跨表)
        int diff = 0;
        diff = Integer.parseInt(params.get("id").toString()) == 39 && compareDate(String.valueOf(date.get("end")),Calendar.getInstance().getTime()) != 0 ? countTimeDiff(date)+1 : countTimeDiff(date);
        for(int i=0;i<diff;i++){
            //格式化时间片段(_201905/_201906、_2018/_2019)
            String dt = formatTimePart(date.get("start").toString(), i);
            //添加进集合
            res.add(tablename+dt);
        }
        return res;
    }



    /**
     * @author: tianyong
     * @time: 2019/6/19 11:09
     * @description:组装动态表名
     */
    public static List<String> dynamicTableName(String tablename,Map<String,Object> params) {
        List<String> lists = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(tablename).append("_");
        Map date = (Map) params.get("dt");
        sb.append(formatTimeByReduceDay(date.get("start").toString()));
        lists.add(sb.toString());
        return lists;
    }



    /**
      * @author: tianyong
      * @time: 2019/7/1 14:05
      * @description:计算时间差
      */
    public static int countTimeDiff(Map time){
        String begin = time.get("start").toString();
        String end = time.get("end").toString();
        String format = begin.length() == 7 ? "yyyy-MM" : "yyyy-MM-dd";
        Period between = null;
        try {
            LocalDate begin_localDate = date2LocalDate(new SimpleDateFormat(format).parse(begin));
            LocalDate end_localDate = date2LocalDate(new SimpleDateFormat(format).parse(end));
            between = Period.between(begin_localDate, end_localDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return begin.length() == 7 ? between.getYears()+1 : between.getMonths()+1;
    }



    /**
      * @author: tianyong
      * @time: 2019/7/1 14:59
      * @description:格式化时间片段
      */
    public static String formatTimePart(String date,int size){
        LocalDate lds = null;
        String format = date.length() == 7 ? "yyyy-MM" : "yyyy-MM-dd";
        try {
            LocalDate ld = date2LocalDate(new SimpleDateFormat(format).parse(date));
            lds = date.length() == 7 ? ld.plusYears(size) : ld.plusMonths(size);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String ss = lds.toString().replace("-", "");
        ss = date.length() == 7 ? ss.substring(0, ss.toString().length() - 4) : ss.substring(0, ss.toString().length() - 2);
        return "_"+ss;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/30 11:47
      * @description: 格式化时间(reduce_day)
      */
    public static String formatTimeByReduceDay(String date){
        return date.replace("-","").substring(0,6);
    }


}
