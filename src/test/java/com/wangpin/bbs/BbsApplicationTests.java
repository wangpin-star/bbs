package com.wangpin.bbs;

import com.sun.corba.se.impl.orbutil.LogKeywords;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.service.TopicService;
import com.wangpin.bbs.userManage.bean.Sign;
import com.wangpin.bbs.userManage.bean.User;
import com.wangpin.bbs.userManage.mapper.SignMapper;
import com.wangpin.bbs.userManage.service.PermissionService;
import com.wangpin.bbs.userManage.service.UserService;
import com.wangpin.bbs.utils.ResultDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class BbsApplicationTests {
    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;
    @Autowired
    SignMapper signMapper;
    @Test
    void contextLoads() {

        ResultDomain resultDomain = userService.querySignDataByUid(1);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now=LocalDate.now();
        System.out.println(signMapper.queryTodaySignDataByUid(1));

    }


}
