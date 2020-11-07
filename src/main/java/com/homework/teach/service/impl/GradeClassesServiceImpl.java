package com.homework.teach.service.impl;

import com.homework.teach.domain.GradeClasses;
import com.homework.teach.mapper.GradeClassesMapper;
import com.homework.teach.service.IGradeClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeClassesServiceImpl implements IGradeClassesService {
    @Autowired
    private GradeClassesMapper gradeClassesMapper;

    @Override
    public int insertGradeClasses(GradeClasses gradeClasses) {
        return gradeClassesMapper.insertGradeClasses(gradeClasses);
    }

    @Override
    public int updateGradeClasses(GradeClasses gradeClasses) {
        return gradeClassesMapper.updateGradeClasses(gradeClasses);
    }

    @Override
    public int deleteGradeClasses(int id) {
        return gradeClassesMapper.deleteGradeClasses(id);
    }

    @Override
    public GradeClasses getGradeClasses(int id) {
        return gradeClassesMapper.getGradeClasses(id);
    }

    @Override
    public List<Map> getGradeClassesList(int gradeId) {
        return gradeClassesMapper.getGradeClassesList(gradeId);
    }

    @Override
    public int getGradeClassesByGC(int gradeId, String classes) {
        return gradeClassesMapper.getGradeClassesByGC(gradeId, classes);
    }

    @Override
    public int getClassesCountByGradeId(int gradId) {
        return gradeClassesMapper.getClassesCountByGradeId(gradId);
    }

    @Override
    public int existStudent(int classesId) {
        return gradeClassesMapper.existStudent(classesId);
    }

    @Override
    public int existHomework(int classesId) {
        return gradeClassesMapper.existHomework(classesId);
    }

    @Override
    public int existWorkBook(int classesId) {
        return gradeClassesMapper.existWorkBook(classesId);
    }

}