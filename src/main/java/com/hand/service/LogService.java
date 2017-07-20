package com.hand.service;

import com.hand.entity.Log;
import com.hand.entity.LogQueryParam;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hanlu on 2017/7/14.
 */
public interface LogService {
    void save(Log log);
    void remove(int id);
    List<Log> listLog(LogQueryParam logQueryParam);
    List<Log> listLogForDate(HashMap dateMap);
    List<Log> listLogForUser(HashMap pageHash);
    int totalSize();
}
