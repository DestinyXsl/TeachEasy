package com.homework.teach.service;

import org.springframework.stereotype.Service;
import com.homework.teach.domain.Student;

import java.util.List;
import java.util.Map;

@Service
public interface IStudentService {
    public int insertStudent(Student student);

    public int updateStudent(Student student);

    public int deleteStudent(int id);
    public int deleteStudentHomeworkRecord(int id);

    public Student getStudent(int id);

    public List<Map> getStudentList(Integer gradeId,Integer classes);
}