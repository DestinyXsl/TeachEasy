package com.homework.teach.mapper.sqlProvide;

public class GradeClassesSQLProvider {
    public String getGradeClassesList(Integer gradeId){
        StringBuffer sql = new StringBuffer();
        sql.append("select gc.id,g.grade,gc.classes,gc.create_time,gc.status from grade_classes gc,grade g where gc.grade_id = g.id and g.status = 1 and gc.status=1 ");
        if(gradeId != -500){
            sql.append(" and gc.grade_id = " + gradeId);
        }
        sql.append(" order by gc.grade_id,gc.classes");
        System.out.println("+++++"+sql.toString());
        return sql.toString();
    }
}
