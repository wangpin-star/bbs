package com.wangpin.bbs.topicManage.mapper;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.ReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyMapper {
    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    int countReplyByTopicId(@Param("topicId") Integer topicId);

    int countReplyByUid(@Param("uid") Integer uid);

    List<Reply> queryReplyByTopicId(@Param("topicId") Integer topicId, @Param("offset") Integer offset);

    List<Reply> queryResentlyReplyByUid(@Param("uid") Integer uid);
}