package com.read.data.bi_statistics.cms.handle;

import com.read.data.bi_statistics.cms.dao.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.DAY;

/**
 * @author: tianyong
 * @Time: 2019/5/30 10:45
 * @description:图表处理服务
 */
@Component
public class ChartHandle {


    @Autowired
    private DataMapper dataMapper;
    private static ChartHandle chartHandle;


    @PostConstruct      //(初始化bean之前执行的操作)
    public void init() {
        chartHandle = this;
        chartHandle.dataMapper = this.dataMapper;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/24 16:04
      * @description:动态版本sql拼接
      */
    public String dynamicVersion(Map params){
        List<String> list = chartHandle.dataMapper.getCurrentVersion(params);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++){
            sb.append(",MAX(CASE version WHEN '"+list.get(i)+"' THEN uv ELSE 0 END ) '"+list.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/29 15:31
      * @description:渠道监控_活跃用户
      */
    public String dynamicUvChannels(Map params){
        List<String> allChannels = chartHandle.dataMapper.getAllChannels();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allChannels.size();i++){
            sb.append(",MAX(CASE qumc WHEN '"+allChannels.get(i)+"' THEN uv ELSE 0 END ) '"+allChannels.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/29 17:06
      * @description:渠道监控_新增用户
      */
    public String dynamicNuvChannels(Map params){
        List<String> allChannels = chartHandle.dataMapper.getAllChannels();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allChannels.size();i++){
            sb.append(",MAX(CASE qumc WHEN '"+allChannels.get(i)+"' THEN nuv ELSE 0 END ) '"+allChannels.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/29 17:06
      * @description:渠道监控_阅读用户
      */
    public String dynamicReadUvChannels(Map params){
        List<String> allChannels = chartHandle.dataMapper.getAllChannels();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allChannels.size();i++){
            sb.append(",MAX(CASE qumc WHEN '"+allChannels.get(i)+"' THEN read_uv ELSE 0 END ) '"+allChannels.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/29 17:06
      * @description:动态独立用户sql拼接
      */
    public String dynamicRequestPathByUvs(Map params){
        List<String> allPages = chartHandle.dataMapper.getAllPages();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allPages.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allPages.get(i)+"' THEN uv ELSE 0 END ) '"+allPages.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/7/29 17:06
      * @description:动态平均停留时长sql拼接
      */
    public String dynamicRequestPathByStays(Map params){
        List<String> allPages = chartHandle.dataMapper.getAllPages();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allPages.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allPages.get(i)+"' THEN stay_min ELSE 0 END ) '"+allPages.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/16 17:51
     * @description:动态平台参数解析
     */
    public String dynamicAppType(Map params){
        return params.get("apptype").toString();
    }


    /**
      * @author: tianyong
      * @time: 2019/8/2 15:56
      * @description:动态设备参数解析
      */
    public String dynamicAdviceType(Map params){
        return params.get("advice").toString();
    }



    /**
      * @author: tianyong
      * @time: 2019/7/24 16:05
      * @description:动态天数sql拼接
      */
    public String dynamicDay(Map params){
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<DAY;i++){
            sb.append(",MAX(CASE days WHEN '"+i+"' THEN retention ELSE 0 END ) '"+i+"天后'");
        }
        return sb.toString().substring(1);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/31 14:29
     * @description:动态独立用户事件sql拼接
     */
    public String dynamicUvEvents(Map params){
        List<String> allEvents = chartHandle.dataMapper.getAllEvents();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allEvents.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allEvents.get(i)+"' THEN uv ELSE 0 END ) '"+allEvents.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
     * @author: tianyong
     * @time: 2019/7/31 14:29
     * @description:动态发生次数事件sql拼接
     */
    public String dynamicPvEvents(Map params){
        List<String> allEvents = chartHandle.dataMapper.getAllEvents();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allEvents.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allEvents.get(i)+"' THEN pv ELSE 0 END ) '"+allEvents.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/8/1 11:54
      * @description:动态来源用户数简介页sql拼接
      */
    public String dynamicUvPages(Map params){
        List<String> allPages = chartHandle.dataMapper.getAllPages();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allPages.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allPages.get(i)+"' THEN ref_uv ELSE 0 END ) '"+allPages.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/8/1 11:54
      * @description:动态来源次数简介页sql拼接
      */
    public String dynamicCountPages(Map params){
        List<String> allPages = chartHandle.dataMapper.getAllPages();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<allPages.size();i++){
            sb.append(",MAX(CASE event_name WHEN '"+allPages.get(i)+"' THEN ref_cnt ELSE 0 END ) '"+allPages.get(i).replace(".","_")+"'");
        }
        return sb.toString().substring(1);
    }


    /**
      * @author: tianyong
      * @time: 2019/8/2 10:45
      * @description:动态流失用户sql拼接
      */
    public String dynamicLoseUvs(Map params){
        StringBuilder sb = new StringBuilder();
        for(int i = 30; i>0; i--) {
            //用户分析_流失(特殊处理)
            if (i > 7 && i < 15) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) + ");
            } else if (i == 7) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) '7-14',");
            } else if (i > 15 && i < 31) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) + ");
            } else if (i == 15) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) '15-30', ");
            } else if (i > 1 && i < 7) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) \"" + i + "\",");
            } else if (i == 1) {
                sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) \"" + i + "\"");
            }else {
                if (i != 30) {
                    sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) \"" + i + "\",");
                } else {
                    sb.append("MAX(CASE days WHEN '" + i + "' THEN uv_per ELSE 0 END ) \"" + i + "\"");
                }
            }
        }
        return sb.toString();
    }

}
