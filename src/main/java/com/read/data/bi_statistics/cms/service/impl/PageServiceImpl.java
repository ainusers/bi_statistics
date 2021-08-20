package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.PageMapper;
import com.read.data.bi_statistics.cms.data.dto.PageDTO;
import com.read.data.bi_statistics.cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
  * @author: tianyong
  * @time: 2019/5/21 16:02
  * @description:页面服务
  */
@Service
public class PageServiceImpl implements PageService {


    @Autowired
    private PageMapper pageMapper;


    /**
     * @author: tianyong
     * @time: 2019/5/21 16:01
     * @description:查询当前页面数据
     */
    public PageDTO getCurrentPage(String pageId){
        return pageMapper.getCurrentPage(pageId);
    }



}
