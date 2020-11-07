package com.homework.teach.service.impl;

import com.homework.teach.domain.Subject;
import com.homework.teach.mapper.SubjectMapper;
import com.homework.teach.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public int insertSubject(Subject subject) {
        return subjectMapper.insertSubject(subject);
    }

    @Override
    public int updateSubject(Subject subject) {
        return subjectMapper.updateSubject(subject);
    }

    @Override
    public int deleteSubject(int id) {
        return subjectMapper.deleteSubject(id);
    }

    @Override
    public int existHomework(int id) {
        return subjectMapper.existHomework(id);
    }

    @Override
    public int existWorkBook(int id) {
        return subjectMapper.existWorkBook(id);
    }

    @Override
    public int existSubject(Subject subject) {
        return subjectMapper.existSubject(subject);
    }

    @Override
    public Subject getSubject(int id) {
        return subjectMapper.getSubject(id);
    }

    @Override
    public List<Subject> getSubjectList() {
        return subjectMapper.getSubjectList();
    }
}