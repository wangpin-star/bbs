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
        for (Topic topic:topicService.queryModuleTopic("提问",null,null,1,0,3).getResultData()) {
            System.out.println(topic);
        }
        int totalPage=topicService.count("提问",0,0,0);
        int[] pageResult={-1,-1,-1,-1,-1};
        System.out.println(totalPage);
        int page=8;
        if (page>totalPage)
            page=totalPage-1;
        if (page<0)
            page=0;
        int remainingPages=totalPage-page;
        if (page<4&&totalPage<=4)
            for (int i=0;i<totalPage;i++) {
                pageResult[i]=i;
            }
        if (page<4&&totalPage>4)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=i;
            }
        if (page>=4&&remainingPages>2)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=page+(i-2);//-2 -1 0 1 2
            }
        if (page>=4&&remainingPages<=2)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=totalPage-(4-i)-1;//-2 -1 0 1 2
            }
        for (int i=0;i<pageResult.length;i++){
            System.out.println(pageResult[i]);
        }
    }


}
