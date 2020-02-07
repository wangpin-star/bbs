package com.wangpin.bbs.topicManage.service;
import com.wangpin.bbs.topicManage.bean.ReplyVo;

import java.util.List;

public interface TopicService {
    List<ReplyVo> queryReplyByTopicId(int topicId);
    int addReply(ReplyVo reply);
    int deleteReply(int replyId);
}
