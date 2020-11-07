package com.homework.teach.service;

import org.springframework.stereotype.Service;
import com.homework.teach.domain.GradeClasses;

import java.util.List;
import java.util.Map;

@Service
public interface IGradeClassesService {
    public int insertGradeClasses(GradeClasses gradeClasses);

    public int updateGradeClasses(GradeClasses gradeClasses);

    public int deleteGradeClasses(int id);

    public GradeClasses getGradeClasses(int id);

    public List<Map> getGradeClassesList(int gradeId);

    public int getGradeClassesByGC(int gradeId,String classes);

    public int getClassesCountByGradeId(int gradId);

    public int existStudent(int classesId);
    public int existHomework(int classesId);
    public int existWorkBook(int classesId);
}