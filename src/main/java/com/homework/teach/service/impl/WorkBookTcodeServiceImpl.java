package com.homework.teach.service.impl;

import com.homework.teach.domain.WorkBookTcode;
import com.homework.teach.mapper.WorkBookTcodeMapper;
import com.homework.teach.service.IWorkBookTcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkBookTcodeServiceImpl implements IWorkBookTcodeService {
    @Autowired
    private WorkBookTcodeMapper workBookTcodeMapper;

    @Override
    public int insertWorkBookTcode(WorkBookTcode workBookTcode) {
        return workBookTcodeMapper.insertWorkBookTcode(workBookTcode);
    }

    @Override
    public int updateWorkBookTcode(WorkBookTcode workBookTcode) {
        return workBookTcodeMapper.updateWorkBookTcode(workBookTcode);
    }

    @Override
    public int deleteWorkBookTcode(int id) {
        return workBookTcodeMapper.deleteWorkBookTcode(id);
    }

    @Override
    public WorkBookTcode getWorkBookTcode(int id) {
        return workBookTcodeMapper.getWorkBookTcode(id);
    }
}