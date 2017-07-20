package com.hand.service.impl;

import com.hand.dao.LogMapper;
import com.hand.entity.Log;
import com.hand.entity.LogQueryParam;
import com.hand.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hanlu on 2017/7/14.
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    LogMapper logMapper;
    public void save(Log log) {
        logMapper.insert(log);
    }

    public void remove(int id) {
        logMapper.deleteByPrimaryKey(id);
    }

    public List<Log> listLog(LogQueryParam logQueryParam) {
        return logMapper.selectAllByParam(logQueryParam);
    }

    public List<Log> listLogForDate(HashMap dateMap) {
        return logMapper.selectLogForDate(dateMap);
    }

    public List<Log> listLogForUser(HashMap pageHash) {
        return logMapper.selectLogForUser(pageHash);
    }

    public int totalSize() {
        return logMapper.selectTotalSize();
    }
}
