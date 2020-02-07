package com.wangpin.bbs;

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
    PermissionService permissionService;
    @Test
    void contextLoads() {

    }


}
