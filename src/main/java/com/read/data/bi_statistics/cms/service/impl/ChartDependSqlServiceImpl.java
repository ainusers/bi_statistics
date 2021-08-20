package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.CommonMapper;
import com.read.data.bi_statistics.cms.dao.TableMapper;
import com.read.data.bi_statistics.cms.data.bo.SqlBO;
import com.read.data.bi_statistics.cms.data.bo.StatusCodeBO;
import com.read.data.bi_statistics.cms.data.enums.CodeInfo;
import com.read.data.bi_statistics.cms.pattern.context.FormatReturnStructureFactory;
import com.read.data.bi_statistics.cms.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static com.read.data.bi_statistics.cms.handle.ChartDataHandle.getSqlTemplate;

/**
  * @author: tianyong
  * @time: 2019/7/24 16:16
  * @description:依赖语句图表服务
  */
@Service
public class ChartDependSqlServiceImpl implements ChartService {


    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private TableMapper tableMapper;


    /**
     * @author: tianyong
     * @time: 2019/7/24 16:16
     * @description:组装DTO对象
     */
    @Override
    public Object buildDTO (String sqlTemplate,Map params){
        //获取字段项
        List<Object> tcs = tableMapper.getTableColumn(params.get("id").toString());
        //获取sql集合
        List<SqlBO> bos = getSqlTemplate(sqlTemplate, params);
        List<LinkedHashMap<String, Object>> llhm = commonMapper.queryDataForSortList(bos.get(1).getSqlTemplate().replace("$sql",bos.get(0).getSqlTemplate()));
        //查询结果集为空
        if(llhm.size() == 0) throw new StatusCodeBO(CodeInfo.CHART_FIND_SUCCESS);
        //组装结果集
        List res = new ArrayList();
        res.add(tcs);
        res.add(llhm);
        //调用工厂：通过传递参数中数据展示类别，来调用相应的格式化方法
        // type类型定义的说明
        return FormatReturnStructureFactory.getInstance("com.read.data.bi_statistics.cms.service.impl.Option"+params.get("type")+"ServiceImpl").formatAlgorithm(res);
    }

}
