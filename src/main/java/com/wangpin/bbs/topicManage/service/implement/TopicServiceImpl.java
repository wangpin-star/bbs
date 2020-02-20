package com.wangpin.bbs.topicManage.service.implement;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.ReplyExample;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.bean.TopicExample;
import com.wangpin.bbs.topicManage.mapper.ReplyMapper;
import com.wangpin.bbs.topicManage.mapper.TopicMapper;
import com.wangpin.bbs.topicManage.service.TopicService;
import com.wangpin.bbs.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Resource
    TopicMapper topicDao;
    @Resource
    ReplyMapper replyDao;
    /**
     *
     * @param topicId
     * @return
     */
    @Override
    public ResultDomain<List<Reply>> queryReplyByTopicId(int topicId) {
        ResultDomain<List<Reply>> resultDomain=new ResultDomain<>();
        ReplyExample replyExample=new ReplyExample();
        replyExample.createCriteria().andTopicIdEqualTo(topicId);
        try {
            List<Reply> replies=replyDao.selectByExample(replyExample);
            resultDomain.setResultCode(1);
            resultDomain.setResultMsg("查询成功！");
            resultDomain.setResultData(replies);
            log.info("查询回复成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询失败！原因："+e.getCause());
            log.info("查询回复失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     * 添加回复
     * @param reply
     * @return
     */
    @Override
    public ResultDomain addReply(Reply reply) {
        ResultDomain resultDomain=new ResultDomain<>();
        log.info("");
        try {
            int code=replyDao.insertSelective(reply);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("回复成功！");
            log.info("回复成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("回复失败！原因："+e.getCause());
            log.info("回复失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     * 删除回复，只有发布回复的人才可以删除自己的回复
     * @param replyId
     * @return
     */
    @Override
    public ResultDomain deleteReply(int replyId) {
        ResultDomain resultDomain=new ResultDomain<>();
        try {
            int code=replyDao.deleteByPrimaryKey(replyId);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("删除成功！");
            log.info("删除回复成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            log.info("删除回复失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     * 发布贴子
     * @param topic
     * @return
     */
    @Override
    public ResultDomain addTopic(Topic topic) {
        ResultDomain resultDomain=new ResultDomain<>();
        try {
            int code=topicDao.insertSelective(topic);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("发贴成功！");
            log.info("发帖成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("发帖失败！原因："+e.getCause());
            log.info("发帖失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     * 标记帖子为删除状态
     * @param topicId
     * @return
     */
    @Override
    public ResultDomain deleteTopic(int topicId) {
        ResultDomain resultDomain=new ResultDomain<>();
        Topic topic=new Topic();
        topic.setTopicState("删除");
        topic.setId(topicId);
        try {
            int code=topicDao.updateByPrimaryKeySelective(topic);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("删除成功！");
            log.info("删除贴子成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            log.info("删除帖子失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public ResultDomain<Topic> queryTopicById(int id) {
        ResultDomain resultDomain=new ResultDomain<>();
        try {
            Topic topic=topicDao.selectByPrimaryKey(id);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topic);
            resultDomain.setResultMsg("查询成功！");
            log.info("查询帖子成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            log.info("查询帖子失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     * 查询用户所有帖子
     * @param userId
     * @return
     */
    @Override
    public ResultDomain<List<Topic>> queryUserTopic(int userId) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        TopicExample topicExample=new TopicExample();
        topicExample.createCriteria().andOwnerIdEqualTo(userId);
        try {
            List<Topic> topics=topicDao.selectByExample(topicExample);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topics);
            resultDomain.setResultMsg("查询成功！");
            log.info("查询用户发布贴子成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询失败！原因："+e.getCause());
            log.info("查询用户发布帖子失败，原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    /**
     *
     * @param moduleName
     * @param end
     * @param essence
     * @param page
     * @return
     */
    @Override
    public ResultDomain<List<Topic>> queryModuleTopic(String moduleName,Integer end,Integer essence,int page,int top ,Integer limit) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        int offset=0;
        if (limit!=null)
            offset=page*limit;
        try {
            List<Topic> topics=topicDao.selectByModuleNameOrTopicState(offset,moduleName,end,essence,top,limit);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topics);
            resultDomain.setResultMsg("查询帖子成功！");

        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询帖子失败！原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public int count(String moduleName, Integer end, Integer essence, int top) {
        int count =-1;
        try {
            count=topicDao.count(moduleName,end,essence,top);
            log.info("查询成功");
        }catch (Exception e){
            log.info("查询失败"+e.getMessage());
        }finally {
            return count;
        }
    }

    @Override
    public ResultDomain updateTopic(Topic topic) {
        ResultDomain resultDomain=new ResultDomain<>();
        try {
            int code=topicDao.updateByPrimaryKeySelective(topic);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("更新帖子成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("更新帖子失败！原因："+e.getCause());
            log.info(e.getMessage());
        }finally {
            return resultDomain;
        }
    }
}
