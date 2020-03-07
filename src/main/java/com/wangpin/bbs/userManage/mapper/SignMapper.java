package com.wangpin.bbs.userManage.mapper;

import com.wangpin.bbs.userManage.bean.Sign;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    List<Sign> querySignDataByUid(@Param("uid") Integer uid);

    int countSignDataByUid(@Param("uid") Integer uid);

    List<Sign> queryTodaySignDataByUid(@Param("uid") Integer uid);
}