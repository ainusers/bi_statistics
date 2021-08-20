package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.ComponentMapper;
import com.read.data.bi_statistics.cms.data.dto.LogicComponentDTO;
import com.read.data.bi_statistics.cms.service.ComponentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Utils.str2Map;

/**
  * @author: tianyong
  * @time: 2019/5/16 17:24
  * @description:组件服务
  */
@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentMapper componentMapper;


    /**
      * @author: tianyong
      * @time: 2019/5/17 16:22
      * @description:查询组件信息
      */
    @Override
    public List<LogicComponentDTO> getLogicComponentsByPageId(String pageId) {
        List<LogicComponentDTO> lcDTO = componentMapper.getLogicComponentsByPageId(pageId);
        Map<String, Object> layouts= null;
        for(LogicComponentDTO logic : lcDTO){
            List<Integer> scopes = new ArrayList<>();
            if(!StringUtils.equals(logic.getScope(),"")){
                if(logic.getScope().indexOf(",") == -1){
                    scopes.add(Integer.parseInt(logic.getScope()));
                }else{
                    String[] split = logic.getScope().split(",");
                    for(String s:split){
                        scopes.add(Integer.parseInt(s));
                    }
                }
                logic.setScopes(scopes);
            }
            if(logic.getLayout().indexOf("{") != -1){
                layouts = str2Map(logic.getLayout());
                logic.setLayouts(layouts);
            }
        }
        return lcDTO;
    }
}
