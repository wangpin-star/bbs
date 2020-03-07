package com.wangpin.bbs.topicManage.mapper;

import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.bean.TopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {
    long countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectByModuleNameOrTopicState(@Param("offset") int offset, @Param("moduleName") String moduleName, @Param("end") Integer end, @Param("essence") Integer essence, @Param("top") Integer top, @Param("limit") Integer limit);

    int count(@Param("moduleName") String moduleName, @Param("end") Integer end, @Param("essence") Integer essence, @Param("top") Integer top);

    int countTopicByUid(@Param("uid") Integer uid);

    Topic queryTopicById(@Param("id") Integer id);

    List<Topic> queryTopicOrderByReplyNum();

    List<Topic> queryTopicByKeyword(@Param("keyword") String keyword, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int countTopicByKeyword(@Param("keyword") String keyword, @Param("offset") Integer offset);

    List<Topic> queryResentlyTopicByUid(@Param("uid") Integer uid);

    List<Topic> queryTopicByUid(@Param("uid") Integer uid, @Param("offset") Integer offset, @Param("limit") Integer limit);
}