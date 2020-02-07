package com.wangpin.bbs.userManage.controller;

import com.wangpin.bbs.userManage.bean.Role;
import com.wangpin.bbs.userManage.service.PermissionService;
import com.wangpin.bbs.utils.ResultDomain;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/roleManage")
public class RoleController {
    @Autowired
    PermissionService permissionService;

}
