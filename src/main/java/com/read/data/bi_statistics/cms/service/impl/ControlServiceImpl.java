package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.CommonMapper;
import com.read.data.bi_statistics.cms.dao.ControlMapper;
import com.read.data.bi_statistics.cms.data.dto.ControlDTO;
import com.read.data.bi_statistics.cms.service.ControlService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.URI;
import static com.read.data.bi_statistics.cms.common.Utils.str2ListMap;
import static com.read.data.bi_statistics.cms.common.Utils.str2Map;
import static com.read.data.bi_statistics.cms.handle.ChartDataHandle.regexGetStr;
import static com.read.data.bi_statistics.cms.handle.UIDataHandle.formatReturnDate;

/**
  * @author: tianyong
  * @time: 2019/5/21 16:02
  * @description:控件服务
  */
@Service
public class ControlServiceImpl implements ControlService {


    @Autowired
    private ControlMapper controlMapper;
    @Autowired
    private CommonMapper commonMapper;


    /**
     * @author: tianyong
     * @time: 2019/5/21 19:34
     * @description:查询当前页面的控件数据(通过组件关联)
     */
    public List<ControlDTO> getCurrentControl(String pageId){
        List<ControlDTO> lcDTO = controlMapper.getCurrentControl(pageId);
        List<Map<String, Object>> lds= null;
        Map<String, Object> mds= null;
        List<Map<String, Object>> ldv= null;
        Map<String, Object> mdv= null;
        Map<String, Object> mso= null;
        for(ControlDTO c: lcDTO){
            List<Integer> control_scopes = new ArrayList<>();
            //数据源data_source
            if(c.getData_source().startsWith("[{")){
                lds = str2ListMap(c.getData_source());
                //添加uri
                for(int i=0;i<lds.size();i++){
                    mso = lds.get(i);
                    for(String key : mso.keySet()){
                        if(StringUtils.equals("uri",key)){
                            mso.put(key,URI+mso.get(key));
                        }
                    }
                }
                c.setData_sources(lds);
            }else if(c.getData_source().startsWith("{")){
                mds = str2Map(c.getData_source());
                //添加uri
                for(String key : mds.keySet()){
                    mds.put(key,URI+mds.get(key));
                }
                c.setData_sources(mds);
            }else if(c.getData_source().startsWith("<sql>")){
                List<Map<String, Object>> controlDatas = commonMapper.queryDataForList(regexGetStr(c.getData_source(), "<sql>", "</sql>", 1).get(0));
                List<Map<String, Object>> lists = str2ListMap(c.getDefault_value());
                controlDatas.add(lists.get(0));
                c.setData_sources(controlDatas);
            }
            //默认值default_value
            if(c.getDefault_value().startsWith("[{")){
                ldv = str2ListMap(c.getDefault_value());
                c.setDefault_values(ldv);
            }else if(c.getDefault_value().startsWith("{")){
                mdv = str2Map(c.getDefault_value());
                //解析时间
                formatReturnDate(mdv);
                c.setDefault_values(mdv);
            }
            //作用域control_scope
            if(!StringUtils.equals(c.getControl_scope(),"0")){
                if(c.getControl_scope().indexOf(",") == -1){
                    control_scopes.add(Integer.parseInt(c.getControl_scope()));
                }else{
                    String[] split = c.getControl_scope().split(",");
                    for(String s:split){
                        control_scopes.add(Integer.parseInt(s));
                    }
                }
            }
            c.setScopes(control_scopes);
        }
        return lcDTO;
    }



}
