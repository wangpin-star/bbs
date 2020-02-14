package com.wangpin.bbs.topicManage.service;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.utils.ResultDomain;

import java.util.List;

public interface TopicService {
    ResultDomain<List<Reply>> queryReplyByTopicId(int topicId);
    ResultDomain addReply(Reply reply);
    ResultDomain deleteReply(int replyId);
    ResultDomain addTopic(Topic topic);
    ResultDomain deleteTopic(int topicId);
    ResultDomain<List<Topic>> queryUserTopic(int userId);
    ResultDomain<List<Topic>> queryModuleTopic(String moduleName,Integer end,Integer essence,int page);
}
