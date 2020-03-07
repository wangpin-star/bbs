package com.wangpin.bbs.topicManage.service.implement;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.bean.TopicExample;
import com.wangpin.bbs.topicManage.mapper.ReplyMapper;
import com.wangpin.bbs.topicManage.mapper.TopicMapper;
import com.wangpin.bbs.topicManage.service.TopicService;
import com.wangpin.bbs.userManage.bean.Collect;
import com.wangpin.bbs.userManage.bean.CollectExample;
import com.wangpin.bbs.userManage.mapper.CollectMapper;
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

    @Resource
    CollectMapper collectDao;

    /**
     *
     * @param topicId
     * @return
     */
    @Override
    public ResultDomain<List<Reply>> queryReplyByTopicId(int topicId,int page) {
        ResultDomain<List<Reply>> resultDomain=new ResultDomain<>();

        try {
            List<Reply> replies=replyDao.queryReplyByTopicId(topicId,page*10);
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

    @Override
    public ResultDomain<List<Reply>> queryResentlyReplyByUid(int uid) {
        ResultDomain<List<Reply>> resultDomain=new ResultDomain<>();
        log.info("");
        try {
            resultDomain.setResultData(replyDao.queryResentlyReplyByUid(uid));
            resultDomain.setResultCode(1);
            resultDomain.setResultMsg("查询用户最近回复成功！");
            log.info("查询用户最近回复成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询用户最近回复失败！原因："+e.getCause());
            log.info("查询用户最近回复失败，原因："+e.getCause());
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
            Topic topic=topicDao.queryTopicById(id);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topic);
            resultDomain.setResultMsg("查询成功！");
            log.info("查询帖子成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("删除失败！原因："+e.getCause());
            log.info("查询帖子失败，原因："+e.getMessage());
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
    public ResultDomain<List<Topic>> queryUserTopic(int userId,int page) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        try {
            List<Topic> topics=topicDao.queryTopicByUid(userId,page*10,10);
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

    @Override
    public ResultDomain<List<Topic>> queryResentlyTopicByUid(int uid) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        try {
            List<Topic> topics=topicDao.queryResentlyTopicByUid(uid);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topics);
            resultDomain.setResultMsg("查询用户最近发布贴子成功！");
            log.info("查询用户最近发布贴子成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询用户最近发布贴子失败！原因："+e.getCause());
            log.info("查询用户最近发布贴子失败，原因："+e.getCause());
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
    public ResultDomain<List<Topic>> queryModuleTopic(String moduleName,Integer end,Integer essence,int page,Integer top ,Integer limit) {
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
    public int count(String moduleName, Integer end, Integer essence, Integer top) {
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
            log.info("更新帖子成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("更新帖子失败！原因："+e.getCause());
            log.info(e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public int countReplyByTopicId(Integer topicId) {
        int count = 0;
        try {
            count=replyDao.countReplyByTopicId(topicId);
            log.info("查询帖子回复数成功");
        }catch (Exception e){
            log.info("查询帖子回复数失败"+e.getMessage());
        }finally {
            return count;
        }
    }

    @Override
    public int countReplyByUid(int uid) {
        int count = 0;
        try {
            count=topicDao.countTopicByUid(uid);
            log.info("查询用户回复数成功");
        }catch (Exception e){
            log.info("查询用户回复数失败"+e.getMessage());
        }finally {
            return count;
        }
    }

    @Override
    public ResultDomain<List<Topic>> queryTopicOrderByReplyNum() {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        try {
            resultDomain.setResultData(topicDao.queryTopicOrderByReplyNum());
            resultDomain.setResultMsg("查询热议榜成功！");
            resultDomain.setResultCode(1);
            log.info("查询热议榜成功");
        }catch (Exception e){
            resultDomain.setResultMsg("查询热议榜失败！");
            resultDomain.setResultCode(-1);
            log.info("查询热议榜失败"+e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public ResultDomain<List<Topic>> queryTopicByKeyword(String keyword,int page,int limit) {
        ResultDomain<List<Topic>> resultDomain=new ResultDomain<>();
        int offset=0;
            offset=page*limit;
        try {
            List<Topic> topics=topicDao.queryTopicByKeyword(keyword,offset,limit);
            resultDomain.setResultCode(1);
            resultDomain.setResultData(topics);
            resultDomain.setResultMsg("根据关键字查询帖子成功！");

        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("根据关键字查询帖子失败！原因："+e.getCause());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public int countTopicByKeyword(String keyword, int page) {
        int count = 0;
        try {
            count=topicDao.countTopicByKeyword(keyword,page*10);
            log.info("查询关键字帖子数成功");
        }catch (Exception e){
            log.info("查询关键字帖子数失败"+e.getMessage());
        }finally {
            return count;
        }
    }

    @Override
    public ResultDomain updateReply(Reply reply) {
        ResultDomain resultDomain=new ResultDomain<>();
        try {
            int code=replyDao.updateByPrimaryKeySelective(reply);
            resultDomain.setResultCode(code);
            resultDomain.setResultMsg("更新回复成功！");
            log.info("更新回复成功");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("更新回复失败！原因："+e.getCause());
            log.info(e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public int countTopicByUid(Integer uid) {
        int count = 0;
        try {
            count=topicDao.countTopicByUid(uid);
            log.info("查询用户发帖数成功");
        }catch (Exception e){
            log.info("查询用户发帖数失败"+e.getMessage());
        }finally {
            return count;
        }
    }

    /**
     * 收藏帖子
     * @param collect
     * @return
     */
    @Override
    public ResultDomain addCollect(Collect collect) {
        ResultDomain resultDomain=new ResultDomain();
        try{
            resultDomain.setResultCode(collectDao.insert(collect));
            resultDomain.setResultMsg("收藏成功！");
            log.info("收藏成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("收藏失败！");
            log.error("收藏失败！"+e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public ResultDomain deleteCollect(int topicId,int userId) {
        ResultDomain resultDomain=new ResultDomain();
        CollectExample example=new CollectExample();
        example.createCriteria().andTopicIdEqualTo(topicId);
        example.createCriteria().andUserIdEqualTo(userId);
        try{
            resultDomain.setResultCode(collectDao.deleteByExample(example));
            resultDomain.setResultMsg("取消收藏成功！");
            log.info("取消收藏成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("取消收藏失败！");
            log.error("取消收藏失败！"+e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public ResultDomain<List<Collect>> queryCollect(int topicId,int userId) {
        ResultDomain<List<Collect>> resultDomain=new ResultDomain<>();
        CollectExample example=new CollectExample();
        example.createCriteria().andTopicIdEqualTo(topicId);
        example.createCriteria().andUserIdEqualTo(userId);
        try{
            resultDomain.setResultData(collectDao.selectByExample(example));
            resultDomain.setResultCode(1);
            resultDomain.setResultMsg("查询当前帖子收藏情况成功！");
            log.info("查询当前帖子收藏情况成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询当前帖子收藏情况失败！");
            log.error("查询当前帖子收藏情况失败！"+e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public ResultDomain<List<Collect>> queryUserCollect(int userId,int page) {
        ResultDomain<List<Collect>> resultDomain=new ResultDomain<List<Collect>>();
        try{
            resultDomain.setResultData(collectDao.queryUserCollect(userId,page*10));
            resultDomain.setResultCode(1);
            resultDomain.setResultMsg("查询用户收藏成功！");
            log.info("查询用户收藏成功！");
        }catch (Exception e){
            resultDomain.setResultCode(-1);
            resultDomain.setResultMsg("查询用户收藏失败！");
            log.error("查询用户收藏失败！"+e.getMessage());
        }finally {
            return resultDomain;
        }
    }

    @Override
    public int countUserCollect(int userId) {
        int count = 0;
        try {
            count= collectDao.countUserCollect(userId);
            log.info("查询用户收藏数成功");
        }catch (Exception e){
            log.info("查询用户收藏数失败"+e.getMessage());
        }finally {
            return count;
        }
    }

}
