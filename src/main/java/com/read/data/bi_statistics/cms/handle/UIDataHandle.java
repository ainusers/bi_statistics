package com.read.data.bi_statistics.cms.handle;

import com.read.data.bi_statistics.cms.data.dto.ControlDTO;
import com.read.data.bi_statistics.cms.data.dto.LogicComponentDTO;
import com.read.data.bi_statistics.cms.data.dto.TreeDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.read.data.bi_statistics.cms.common.Utils.countDate;
import static com.read.data.bi_statistics.cms.common.Utils.getCurentDate;

/**
 * @author: tianyong
 * @Time: 2019/5/21 20:30
 * @description:UI服务
 */
public class UIDataHandle {
    //私有构造
    private UIDataHandle(){}


    /**
      * @author: tianyong
      * @time: 2019/5/21 20:47
      * @description:组装组件、控件结构
      */
    public static List<LogicComponentDTO> makeComponentAndControl(List<? extends TreeDTO<LogicComponentDTO>> lc, List<ControlDTO> cl){
        List<LogicComponentDTO> result = new ArrayList<>();
        LogicComponentDTO[] component = {null};
        lc.stream().forEach(l->{
            component[0] = (LogicComponentDTO) l;
            component[0].setControl(cl.stream().filter(c -> (l).getId() == c.getComponent_id()).collect(Collectors.toList()));
            result.add(component[0]);
        });
        return result;
    }


    /**
      * @author: tianyong
      * @time: 2019/7/15 11:19
      * @description:格式化返回时间数据
      */
    public static void formatReturnDate(Map data){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //最新的统计数据是昨天的，故-1
        Date curentDate = countDate(getCurentDate(),-1);
        Date beginDate = countDate(curentDate, -30);
        if(data.containsKey("end")){
            data.put("start",sdf.format(beginDate));
            data.put("end",sdf.format(curentDate));
        }else{
            data.put("start",sdf.format(curentDate));
        }
    }


}
