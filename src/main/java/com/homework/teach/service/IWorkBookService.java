package com.homework.teach.service;

import com.homework.teach.domain.Student;
import org.springframework.stereotype.Service;
import com.homework.teach.domain.WorkBook;

import java.util.List;
import java.util.Map;

@Service
public interface IWorkBookService {
    public int insertWorkBook(WorkBook workBook);

    public int updateWorkBook(WorkBook workBook);
    public int updateWorkBookTcode(WorkBook workBook);

    public int deleteWorkBook(int id);
    public int existHomework(int id);
    public int getWorkBookByName(WorkBook workBook);

    public WorkBook getWorkBook(int id);

    public List<Map> getWorkBookList(int adminId);

    public List<Map> getWorkBookListByAdminId(int adminId,int subjectId,int classesId);

    public String getBaseTcodeById(int workBookId);

    public List<Student> getStudentIdByWorkBook(int gradeClassesId);
    public String getTcodeCN(int workBookId);

    public List<String> getTcodeByWorkBookId(int adminId,int workBookId);

}