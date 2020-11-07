package com.homework.teach.service;

import org.springframework.stereotype.Service;
import com.homework.teach.domain.Subject;

import java.util.List;

@Service
public interface ISubjectService {
    public int insertSubject(Subject subject);

    public int updateSubject(Subject subject);

    public int deleteSubject(int id);
    public int existHomework(int id);
    public int existWorkBook(int id);
    public int existSubject(Subject subject);

    public Subject getSubject(int id);

    public List<Subject> getSubjectList();
}