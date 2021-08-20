package com.read.data.bi_statistics.cms.controller;

import com.read.data.bi_statistics.cms.config.ControllerLogConfig;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.dto.*;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.service.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static com.read.data.bi_statistics.cms.common.Constants.DataType.PAGE_ID;
import static com.read.data.bi_statistics.cms.common.Utils.*;
import static com.read.data.bi_statistics.cms.common.Utils.success;

/**
 * @author: tianyong
 * @Time: 2019/5/16 17:27
 * @description:UI数据 api
 */
@Controller
@RequestMapping("/layout")
public class LayoutController {


    @Autowired
    private MenuService menuService;
    @Autowired
    private MakeDataService makeDataService;


    /**
      * @author: tianyong
      * @time: 2019/5/16 17:27
      * @description:根据当前用户信息查询菜单树形结构数据
      */
    @CrossOrigin
    @ResponseBody
    @PostMapping("/menu")
    @RequiresAuthentication
    @ControllerLogConfig(description = "获取菜单数据api")
    public Object getMenusByUserName(HttpServletRequest request){
        //获取参数
        Map<String, Object> params = getPostRequestParams(request);
        if(params.get("username") == null) return error(CodeInfo.MENU_INVALID_PARAM);
        List<MenuDTO> result = menuService.getMenusByUserName((String) params.get("username"));
        if(result == null)  throw new StatusCodeBO(CodeInfo.CHART_FIND_SUCCESS);
        return success(result);
    }


    /**
      * @author: tianyong
      * @time: 2019/5/17 16:24
      * @description:查询组件树形结构数据
      */
    @CrossOrigin
    @ResponseBody
    @PostMapping("/page")
    @RequiresAuthentication
    @ControllerLogConfig(description = "获取页面数据api")
    public Object getPages(HttpServletRequest request){
        //获取参数
        Map<String, Object> params = getPostRequestParams(request);
        if(params.get(PAGE_ID) == null) return error(CodeInfo.PAGE_INVALID_PARAM);
        return  success(makeDataService.makeUIBoneData(params));
    }


}
