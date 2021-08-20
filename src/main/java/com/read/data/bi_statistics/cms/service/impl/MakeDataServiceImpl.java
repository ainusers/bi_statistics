package com.read.data.bi_statistics.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.read.data.bi_statistics.cms.common.Constants;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.dto.ControlDTO;
import com.read.data.bi_statistics.cms.data.dto.LogicComponentDTO;
import com.read.data.bi_statistics.cms.data.dto.PageAndComponentDTO;
import com.read.data.bi_statistics.cms.data.dto.PageDTO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.service.ComponentService;
import com.read.data.bi_statistics.cms.service.ControlService;
import com.read.data.bi_statistics.cms.service.MakeDataService;
import com.read.data.bi_statistics.cms.service.PageService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.PAGE_ID;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;
import static com.read.data.bi_statistics.cms.common.Utils.makeDoubleObject2One;
import static com.read.data.bi_statistics.cms.common.Utils.makeTreeBones;
import static com.read.data.bi_statistics.cms.handle.UIDataHandle.makeComponentAndControl;

/**
 * @author: tianyong
 * @Time: 2019/8/29 16:53
 * @description:调用service服务层
 */
@Component
public class MakeDataServiceImpl implements MakeDataService {


    @Autowired
    private PageService pageService;
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ControlService controlService;


    /**
     * @author: tianyong
     * @time: 2019/8/29 16:31
     * @description:組裝UI结构数据
     */
    public PageAndComponentDTO makeUIBoneData(Map<String, Object> params){
        //当前页面数据
        Object obj_page = pageService.getCurrentPage(params.get(PAGE_ID).toString());
        //当前页面组件数据
        Object obj_components = componentService.getLogicComponentsByPageId(params.get(PAGE_ID).toString());
        //当前页面控件数据
        Object obj_controls = controlService.getCurrentControl(params.get(PAGE_ID).toString());

        //转换数据类型
        PageDTO page = JSONObject.parseObject(String.valueOf(obj_page), PageDTO.class);
        List<LogicComponentDTO> components = JSONArray.parseArray(String.valueOf(JSON.toJSON(obj_components)), LogicComponentDTO.class);
        List<ControlDTO> controls = JSONArray.parseArray(String.valueOf(JSON.toJSON(obj_controls)), ControlDTO.class);

        //组装组件、控件数据
        List<LogicComponentDTO> llc = makeComponentAndControl(components, controls);
        //组装组件自身结构数据
        List<LogicComponentDTO> res = makeTreeBones(llc);
        //页面_组件DTO对象
        PageAndComponentDTO pac = new PageAndComponentDTO();
        try {
            //组装页面和组件的结构
            BeanUtils.copyProperties(pac,makeDoubleObject2One(page, ImmutableMap.of(Constants.DataType.COMPONENT, res)));
        } catch (Exception e) {
            loggers.error("组装页面和组件结构数据错误!",e);
        }
        if(pac.getComponent().size() == 0)  throw new StatusCodeBO(CodeInfo.CHART_FIND_SUCCESS);
        return pac;
    }

}
