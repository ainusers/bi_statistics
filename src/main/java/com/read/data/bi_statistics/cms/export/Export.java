package com.read.data.bi_statistics.cms.export;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.read.data.bi_statistics.cms.common.Utils;
import com.read.data.bi_statistics.cms.config.ControllerLogConfig;
import com.read.data.bi_statistics.cms.config.FileConfig;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.pattern.context.ServiceFactory;
import com.read.data.bi_statistics.cms.service.ChartService;
import com.read.data.bi_statistics.cms.service.TableService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.common.Utils.loggers;
import static com.read.data.bi_statistics.cms.common.Utils.map2Entity;


/**
  * @author: tianyong
  * @time: 2019/8/2 18:15
  * @description:导出excel
  */
@Controller
@RequestMapping("/export")
public class Export {


    @Autowired
    private TableService tableService;
    @Autowired
    private FileConfig fileConfig;


    /**
      * @author: tianyong
      * @time: 2019/8/2 18:14
      * @description:导出excel
      */
    @CrossOrigin
    @ResponseBody
    @GetMapping(value = "/excel/{id}")
    @ControllerLogConfig(description = "excel导出api")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, @PathVariable("id")String id){
        //获取excel对应entity对象
        String[] excels = fileConfig.getContents().get(id).split(",");
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.read.data.bi_statistics.cms.data.export."+excels[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取传递参数
        Map<String, Object> ms = Utils.getRequestParams(request);
        //格式化数据
        Map<String, Object> tis = tableService.getTableInfo(id);
        ms.put("id",id);
        ms.put("type",tis.get("show_type").toString());
        //给接口动态注入实现类 (class_name：需要和类名保持一致(首字母小写)，最终需纳入文档)
        ChartService csi = new ServiceFactory(ChartService.class).getServices(tis.get("class_name").toString());
        //通过具体实现类组装DTO
        Object object = csi.buildDTO(tis.get("mysql_template").toString(), ms);
        List rows = (List<Object>) object;
        //maptoEntity数据转换
        List<Object> list = new ArrayList<>();
        for(int i=0;i<rows.size();i++){
            List<LinkedHashMap<String, Object>> lists = (List<LinkedHashMap<String, Object>>) rows.get(i);
            for(int j=0;j<lists.size();j++){
                try {
                    Object o = clazz.newInstance();
                    map2Entity((Map<String, Object>) lists.get(j),o);
                    list.add(o);
                } catch (Exception e) {
                    loggers.error("map转换entity错误!", e);
                    throw new StatusCodeBO(CodeInfo.TRANS_DATA_TYPE_FAIL);
                }
            }
        }
        //easypoi导出excel
        String filename = excels[1];
        ExportParams params = new ExportParams(excels[2],excels[3]);
        Workbook workbook = ExcelExportUtil.exportExcel(params,clazz,list);
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-Type","application/vnd.ms-excel");
        try {
            filename = new String(filename.getBytes("GB2312"), "ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename="+filename+".xls");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            //打印错误log
            loggers.error("导出Excel-IO错误!", e);
            throw new StatusCodeBO(CodeInfo.EXPORT_EXCEL_FAIL);
        }
    }

}
