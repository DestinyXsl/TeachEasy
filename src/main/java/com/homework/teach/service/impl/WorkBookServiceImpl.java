package com.homework.teach.service.impl;

import com.homework.teach.domain.Student;
import com.homework.teach.domain.WorkBook;
import com.homework.teach.mapper.WorkBookMapper;
import com.homework.teach.service.IWorkBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkBookServiceImpl implements IWorkBookService {
    @Autowired
    private WorkBookMapper workBookMapper;

    @Override
    public int insertWorkBook(WorkBook workBook) {
        return workBookMapper.insertWorkBook(workBook);
    }

    @Override
    public int updateWorkBook(WorkBook workBook) {
        return workBookMapper.updateWorkBook(workBook);
    }

    @Override
    public int updateWorkBookTcode(WorkBook workBook) {
        return workBookMapper.updateWorkBookTcode(workBook);
    }

    @Override
    public int deleteWorkBook(int id) {
        return workBookMapper.deleteWorkBook(id);
    }

    @Override
    public int existHomework(int id) {
        return workBookMapper.existHomework(id);
    }

    @Override
    public int getWorkBookByName(WorkBook workBook) {
        return workBookMapper.getWorkBookByName(workBook);
    }

    @Override
    public WorkBook getWorkBook(int id) {
        return workBookMapper.getWorkBook(id);
    }

    @Override
    public List<Map> getWorkBookList(int adminId) {
        return workBookMapper.getWorkBookList(adminId);
    }

    @Override
    public List<Map> getWorkBookListByAdminId(int adminId,int subjectId,int classesId) {
        return workBookMapper.getWorkBookListByAdminId(adminId,subjectId,classesId);
    }

    @Override
    public String getBaseTcodeById(int workBookId) {
        return workBookMapper.getBaseTcodeById(workBookId);
    }

    @Override
    public List<Student> getStudentIdByWorkBook(int gradeClassesId) {
        return workBookMapper.getStudentIdByWorkBook(gradeClassesId);
    }

    @Override
    public String getTcodeCN(int workBookId) {
        return workBookMapper.getTcodeCN(workBookId);
    }

    @Override
    public List<String> getTcodeByWorkBookId(int adminId,int workBookId) {
        return workBookMapper.getTcodeByWorkBookId(adminId,workBookId);
    }
}