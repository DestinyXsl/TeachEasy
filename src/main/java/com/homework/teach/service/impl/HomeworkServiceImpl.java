package com.homework.teach.service.impl;

import com.homework.teach.domain.Homework;
import com.homework.teach.domain.Student;
import com.homework.teach.mapper.HomeworkMapper;
import com.homework.teach.service.IHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeworkServiceImpl implements IHomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public int insertHomework(Homework homework) {
        return homeworkMapper.insertHomework(homework);
    }

    @Override
    public int updateHomework(Homework homework) {
        return homeworkMapper.updateHomework(homework);
    }

    @Override
    public int deleteHomework(int id) {
        return homeworkMapper.deleteHomework(id);
    }

    @Override
    public int deleteHomeworkRecord(int id) {
        return homeworkMapper.deleteHomeworkRecord(id);
    }

    @Override
    public Homework getHomework(int id) {
        return homeworkMapper.getHomework(id);
    }

    @Override
    public List<Map> getHomeworkList(int adminId) {
        return homeworkMapper.getHomeworkList(adminId);
    }

    @Override
    public List<Student> getStudentIdByHomework(int homeworkId) {
        return homeworkMapper.getStudentIdByHomework(homeworkId);
    }

    @Override
    public int getHomeworkStatus(int homeworkId) {
        return homeworkMapper.getHomeworkStatus(homeworkId);
    }

    @Override
    public List<Map> getHomeworkStatisticalList(int adminId) {
        return homeworkMapper.getHomeworkStatisticalList(adminId);
    }

    @Override
    public List<Map> getChart(int gradeClassesId, int subjectId) {
        return homeworkMapper.getChart(gradeClassesId, subjectId);
    }
}