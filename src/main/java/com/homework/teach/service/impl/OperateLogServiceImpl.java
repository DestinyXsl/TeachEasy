package com.homework.teach.service.impl;


import com.homework.teach.domain.OperateLog;

import com.homework.teach.mapper.OperateLogMapper;
import com.homework.teach.service.IOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements IOperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public int insertOperateLog(OperateLog operateLog) {
        return operateLogMapper.insertOperateLog(operateLog);
    }

    @Override
    public int updateOperateLog(OperateLog operateLog) {
        return operateLogMapper.updateOperateLog(operateLog);
    }

    @Override
    public int deleteOperateLog(int id) {
        return operateLogMapper.deleteOperateLog(id);
    }

    @Override
    public OperateLog getOperateLog(int id) {
        return operateLogMapper.getOperateLog(id);
    }

    @Override
    public List<OperateLog> getOperateLogList(String nameOrMenu, String startTime, String endTime) {
        return operateLogMapper.getOperateLogList(nameOrMenu, startTime, endTime);
    }
}