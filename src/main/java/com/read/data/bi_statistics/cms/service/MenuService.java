package com.read.data.bi_statistics.cms.service;

import com.read.data.bi_statistics.cms.data.dto.MenuDTO;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;

/**
  * @author: tianyong
  * @time: 2019/5/16 17:22
  * @description:菜单接口
  */
public interface MenuService {

    /**
      * @author: tianyong
      * @time: 2019/5/16 17:23
      * @description:根据当前用户信息查询菜单信息
      */
    @Cacheable(key = "'menuBoneData_'+#root.methodName+'_'+#root.args",value = "redis-cache-always")
    List<MenuDTO> getMenusByUserName(String username);

}
