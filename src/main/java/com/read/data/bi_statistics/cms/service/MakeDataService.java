package com.read.data.bi_statistics.cms.service;

import com.read.data.bi_statistics.cms.data.dto.PageAndComponentDTO;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/8/29 16:52
 * @description:调用service服务层
 */
public interface MakeDataService {


    /**
     * @author: tianyong
     * @time: 2019/8/29 16:31
     * @description:組裝UI结构数据
     */
    PageAndComponentDTO makeUIBoneData(Map<String, Object> params);

}
