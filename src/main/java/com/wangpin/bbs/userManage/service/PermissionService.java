package com.wangpin.bbs.userManage.service;

import com.wangpin.bbs.userManage.bean.Role;
import com.wangpin.bbs.utils.ResultDomain;

import java.util.List;

public interface PermissionService {
     ResultDomain<List<Role>> queryRole(int id);
     ResultDomain<List<Role>> queeryByUid(int id);
     ResultDomain addRole(Role role);
     ResultDomain deleteRole(int uid);
}
