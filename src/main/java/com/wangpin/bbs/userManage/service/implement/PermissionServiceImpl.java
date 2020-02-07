package com.wangpin.bbs.userManage.service.implement;

import com.wangpin.bbs.userManage.bean.Role;
import com.wangpin.bbs.userManage.bean.RoleExample;
import com.wangpin.bbs.userManage.mapper.RoleMapper;
import com.wangpin.bbs.userManage.service.PermissionService;
import com.wangpin.bbs.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    RoleMapper roleDao;

    /**
     * 只提供uid，且uid不为0，其他参数为0，查询用户所有角色.提供moduleId和uid查询用户拥有的版主角色。
     * 提供uid和topicId查询用户拥有的贴主角色
     * @param id
     * @return
     */
    @Override
    public ResultDomain<List<Role>> queryRole(int id) {
        log.info("查询所有角色");
        ResultDomain resultDomain=new ResultDomain();
        RoleExample example=new RoleExample();
        example.createCriteria().andIdEqualTo(id);
        if (id==0){
            resultDomain.setResultMsg("请提供正确的id");
            resultDomain.setResultCode(-2);
        }

        try{
            resultDomain.setResultData(roleDao.selectByExample(example));
            resultDomain.setResultMsg("查询成功");
            resultDomain.setResultCode(1);
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询失败");
        }finally {
            return  resultDomain;
        }

    }

    /**
     *根据用户id查询用户所有角色
     * @param uid
     * @return
     */
    @Override
    public ResultDomain<List<Role>> queeryByUid(int uid) {
        log.info("查询用户拥有的角色");
        ResultDomain resultDomain=new ResultDomain();
        RoleExample roleExample =new RoleExample();
        roleExample.createCriteria().andUserIdEqualTo(uid);
        try{
            resultDomain.setResultData(roleDao.selectByExample(roleExample));
            resultDomain.setResultMsg("查询成功");
            resultDomain.setResultCode(1);
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询出错");
        }finally {
            return  resultDomain;
        }
    }

    /**
     *
     * @param role
     * @return
     */
    @Override
    public ResultDomain addRole(Role role) {
        log.info("添加角色");
        ResultDomain resultDomain=new ResultDomain();
        try{
            resultDomain.setResultData(roleDao.insert(role));
            resultDomain.setResultMsg("添加成功");
            resultDomain.setResultCode(1);
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("添加出错");
        }finally {
            return  resultDomain;
        }
    }

    /**
     * 必须提供uid，且uid不为0.
     * @param uid
     * @return
     */
    @Override
    public ResultDomain deleteRole(int uid) {
        log.info("删除用户角色");
        ResultDomain resultDomain=new ResultDomain();
        RoleExample example=new RoleExample();
        example.createCriteria().andIdEqualTo(uid);
        if (uid==0){
            resultDomain.setResultMsg("请提供正确的uid");
            resultDomain.setResultCode(-2);
        }
        try{
            resultDomain.setResultData(roleDao.deleteByExample(example));
            resultDomain.setResultMsg("删除成功");
            resultDomain.setResultCode(1);
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败");
        }finally {
            return  resultDomain;
        }

    }
}
