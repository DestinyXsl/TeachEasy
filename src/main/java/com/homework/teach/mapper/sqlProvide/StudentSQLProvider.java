package com.homework.teach.mapper.sqlProvide;

public class StudentSQLProvider {
    public String getStudentList(Integer gradeId,Integer classes){
        StringBuffer sql = new StringBuffer();
        sql.append("select g.id as gId,g.grade,gc.classes,s.* from student s,grade g,grade_classes gc where s.`status` = 1 and s.grade_classes_id = gc.id and gc.grade_id = g.id");
        if(gradeId != -500){
            sql.append(" and g.id = " + gradeId);
        }
        if(classes != -500){
            sql.append(" and s.grade_classes_id = " + classes);
        }
        sql.append(" order by gc.grade_id,gc.classes,s.seat_number");
        System.out.println("+++++"+sql.toString());
        return sql.toString();
    }
}
