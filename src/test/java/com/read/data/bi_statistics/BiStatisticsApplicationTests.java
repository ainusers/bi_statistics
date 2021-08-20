package com.read.data.bi_statistics;

import com.read.data.bi_statistics.cms.service.ComponentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BiStatisticsApplicationTests {

    @Autowired
    private ComponentService componentService;

    @Test
    public void contextLoads() {

    }

}





