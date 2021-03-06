package com.wangpin.bbs.userManage.mapper;

import com.wangpin.bbs.userManage.bean.Collect;
import com.wangpin.bbs.userManage.bean.CollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectMapper {
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> queryUserCollect( @Param("userId") Integer userId,@Param("offset") Integer offset);

    int countUserCollect(@Param("userId") Integer userId);
}