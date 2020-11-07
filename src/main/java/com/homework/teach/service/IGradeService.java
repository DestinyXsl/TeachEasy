package com.homework.teach.service;

import org.springframework.stereotype.Service;
import com.homework.teach.domain.Grade;

import java.util.List;
import java.util.Map;

@Service
public interface IGradeService {
    public int insertGrade(Grade grade);

    public int updateGrade(Grade grade);

    public int deleteGrade(int id);

    public Grade getGrade(int id);

    public int existGrade(Grade grade);

    public List<Grade> getGradeList();


}