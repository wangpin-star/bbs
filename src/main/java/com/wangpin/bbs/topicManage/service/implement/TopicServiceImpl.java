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
import org.apache.ibatis.annotations.Param;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询失败！原因："+e.getCause());
            return resultDomain;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("回复失败！原因："+e.getCause());
            return resultDomain;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            return resultDomain;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("发帖失败！原因："+e.getCause());
            return resultDomain;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            return resultDomain;
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
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            return resultDomain;
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
    public ResultDomain<List<Topic>> queryModuleTopic(String moduleName,Integer end,Integer essence,int page,Integer top) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        int offset=page*10;
        try {
            List<Topic> topics=topicDao.selectByModuleNameOrTopicState(offset,moduleName,end,essence,top);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topics);
            resultDomain.setResultMsg("查询成功！");

        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询失败！原因："+e.getCause());
            return resultDomain;
        }finally {
            return resultDomain;
        }
    }
}
