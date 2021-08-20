package com.read.data.bi_statistics.cms.controller;

import com.read.data.bi_statistics.cms.common.Utils;
import com.read.data.bi_statistics.cms.config.ControllerLogConfig;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.entity.ReturnBone;
import com.read.data.bi_statistics.cms.pattern.context.ServiceFactory;
import com.read.data.bi_statistics.cms.service.ChartService;
import com.read.data.bi_statistics.cms.service.TableService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Utils.error;
import static com.read.data.bi_statistics.cms.common.Utils.success;

/**
 * @author: tianyong
 * @Time: 2019/6/20 20:33
 * @description:数据api
 */
@Controller
@RequestMapping("/data")
public class DataController {


    @Autowired
    private TableService tableService;


    /**
     * @author: tianyong
     * @time: 2019/6/20 16:33
     * @description:获取图表数据
     */
    @CrossOrigin
    @ResponseBody
    @RequiresAuthentication
    @PostMapping("/chart/{id}")
    @ControllerLogConfig(description = "获取图表数据api")
    public ReturnBone chart(HttpServletRequest request, @PathVariable("id")String id) {
        //解析传递参数
        Map<String, Object> params = Utils.getPostRequestParams(request);
        //根据id获取具体实现类名称、sql模板、格式化数据类别type
        Map<String, Object> tis = tableService.getTableInfo(id);
        //返回图表参数无效错误信息
        if(tis == null) return error(CodeInfo.CHART_INVALID_PARAM);
        params.put("id",id);
        params.put("type",tis.get("show_type").toString());
        //给接口动态注入实现类 (class_name：需要和类名保持一致(首字母小写)，最终需纳入文档)
        ChartService csi = new ServiceFactory(ChartService.class).getServices(tis.get("class_name").toString());
        //通过具体实现类组装DTO
        Object result = csi.buildDTO(tis.get("mysql_template").toString(), params);
        Map map = (Map) result;
        List datas = (List) map.get("source");
        if(datas.size() == 0)  throw new StatusCodeBO(CodeInfo.CHART_FIND_SUCCESS);
        return success(result);
    }

}
