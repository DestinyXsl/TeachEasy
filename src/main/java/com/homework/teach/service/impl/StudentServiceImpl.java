package com.homework.teach.service.impl;

import com.homework.teach.domain.Student;
import com.homework.teach.mapper.StudentMapper;
import com.homework.teach.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int deleteStudent(int id) {
        return studentMapper.deleteStudent(id);
    }

    @Override
    public int deleteStudentHomeworkRecord(int id) {
        return studentMapper.deleteStudentHomeworkRecord(id);
    }

    @Override
    public Student getStudent(int id) {
        return studentMapper.getStudent(id);
    }

    @Override
    public List<Map> getStudentList(Integer gradeId,Integer classes) {

        return studentMapper.getStudentList(gradeId, classes);
    }
}