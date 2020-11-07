package com.homework.teach.service;

import com.homework.teach.domain.Student;
import org.springframework.stereotype.Service;
import com.homework.teach.domain.Homework;

import java.util.List;
import java.util.Map;

@Service
public interface IHomeworkService {
    public int insertHomework(Homework homework);

    public int updateHomework(Homework homework);

    public int deleteHomework(int id);

    public int deleteHomeworkRecord(int id);

    public Homework getHomework(int id);

    public List<Map> getHomeworkList(int adminId);

    public List<Student> getStudentIdByHomework(int homeworkId);

    public int getHomeworkStatus(int homeworkId);

    public List<Map> getHomeworkStatisticalList(int adminId);

    public List<Map> getChart(int gradeClassesId,int subjectId);
}