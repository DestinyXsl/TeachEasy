package com.homework.teach.mapper.sqlProvide;


import com.homework.teach.domain.Student;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

public class HomeworkRecordSQLProvider {
    public String insertHomeworkRecord(int homeworkId,List<Student> students,String tcode){
        StringBuffer sql = new StringBuffer();
        DecimalFormat g1=new DecimalFormat("00000");
        sql.append("insert into homework_record (home_work_id,student_id,tcode,record_status,level,create_time,update_time,`status`) values ");
        for(Student s : students){

            sql.append("("+homeworkId+","+s.getId()+",'"+tcode+g1.format(s.getSeatNumber())+"',0,0,sysdate(),sysdate(),1),");
        }
        sql.deleteCharAt(sql.length()-1);
        return sql.toString();
    }
}
