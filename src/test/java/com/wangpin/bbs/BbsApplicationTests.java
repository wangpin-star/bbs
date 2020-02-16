package com.wangpin.bbs;

import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.service.TopicService;
import com.wangpin.bbs.userManage.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class BbsApplicationTests {
    @Autowired
    TopicService topicService;
    @Test
    void contextLoads() {
        for (Topic topic:topicService.queryModuleTopic(null,null,null,0,null).getResultData()) {
            System.out.println(topic);
        }
    }


}
