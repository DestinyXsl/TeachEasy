package com.homework.teach.service.impl;

import com.homework.teach.domain.Grade;
import com.homework.teach.mapper.GradeMapper;
import com.homework.teach.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements IGradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public int insertGrade(Grade grade) {
        return gradeMapper.insertGrade(grade);
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }

    @Override
    public int deleteGrade(int id) {
        return gradeMapper.deleteGrade(id);
    }

    @Override
    public Grade getGrade(int id) {
        return gradeMapper.getGrade(id);
    }

    @Override
    public int existGrade(Grade grade) {
        return gradeMapper.existGrade(grade);
    }

    @Override
    public List<Grade> getGradeList() {
        return gradeMapper.getGradeList();
    }
}