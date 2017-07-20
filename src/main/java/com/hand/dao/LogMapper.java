package com.hand.dao;

import com.hand.entity.Log;
import com.hand.entity.LogQueryParam;

import java.util.HashMap;
import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> selectAllByParam(LogQueryParam logQueryParam);

    List<Log> selectLogForUser(HashMap pageHash);

    List<Log> selectLogForDate(HashMap dateMap);

    int selectTotalSize();
}