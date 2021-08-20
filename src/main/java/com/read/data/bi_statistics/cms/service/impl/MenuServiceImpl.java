package com.read.data.bi_statistics.cms.service.impl;

import com.read.data.bi_statistics.cms.dao.MenuMapper;
import com.read.data.bi_statistics.cms.data.dto.MenuDTO;
import com.read.data.bi_statistics.cms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.read.data.bi_statistics.cms.common.Utils.makeTreeBones;

/**
  * @author: tianyong
  * @time: 2019/5/16 17:24
  * @description:菜单服务
  */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
      * @author: tianyong
      * @time: 2019/5/16 17:24
      * @description:根据当前用户信息查询菜单信息
      */
    @Override
    public List<MenuDTO> getMenusByUserName(String username) {
        return makeTreeBones(menuMapper.getMenusByUserName(username));
    }
}
