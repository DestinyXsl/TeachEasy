package com.homework.teach.service;


import com.homework.teach.domain.OperateLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOperateLogService {
    public int insertOperateLog(OperateLog operateLog);

    public int updateOperateLog(OperateLog operateLog);

    public int deleteOperateLog(int id);

    public OperateLog getOperateLog(int id);

    public List<OperateLog> getOperateLogList(String nameOrMenu, String startTime, String endTime);
}