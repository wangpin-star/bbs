package com.wangpin.bbs.topicManage.service;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.userManage.bean.Collect;
import com.wangpin.bbs.utils.ResultDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicService {
    ResultDomain<List<Reply>> queryReplyByTopicId(int topicId,int page);
    ResultDomain<List<Reply>> queryResentlyReplyByUid(int uid);
    ResultDomain addReply(Reply reply);
    ResultDomain deleteReply(int replyId);
    ResultDomain addTopic(Topic topic);
    ResultDomain deleteTopic(int topicId);
    ResultDomain<Topic> queryTopicById(int id);
    ResultDomain<List<Topic>> queryUserTopic(int userId,int page);
    ResultDomain<List<Topic>> queryResentlyTopicByUid(int uid);
    ResultDomain<List<Topic>> queryModuleTopic(String moduleName,Integer end,Integer essence,int page,Integer top,Integer limit);
    int count(String moduleName,Integer end,Integer essence,Integer top);
    ResultDomain updateTopic(Topic topic);
    int countTopicByUid(Integer uid);
    int countReplyByTopicId(Integer topicId);
    int countReplyByUid(int uid);
    ResultDomain<List<Topic>> queryTopicOrderByReplyNum();
    ResultDomain<List<Topic>> queryTopicByKeyword(String keyword, int page,int limit);
    int countTopicByKeyword(String keyword,int page);
    ResultDomain updateReply(Reply reply);
    ResultDomain addCollect(Collect collect);
    ResultDomain deleteCollect(int topicId,int userId);
    ResultDomain<List<Collect>> queryCollect(int topicId,int userId);
    ResultDomain<List<Collect>> queryUserCollect(int userId,int page);
    int countUserCollect(int userId);
}
