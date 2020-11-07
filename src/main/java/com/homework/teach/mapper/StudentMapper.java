package com.homework.teach.mapper;

import com.homework.teach.mapper.sqlProvide.StudentSQLProvider;
import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Insert("INSERT into student (grade_classes_id,name,sex,telephone,seat_number,create_time,update_time,status) values (#{gradeClassesId},#{name},#{sex},#{telephone},#{seatNumber},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertStudent(Student student);

    @Update("UPDATE student SET grade_classes_id=#{gradeClassesId},name=#{name},sex=#{sex},telephone=#{telephone},seat_number=#{seatNumber},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateStudent(Student student);

//    @Delete("delete from student where id=#{id}")
    @Update("UPDATE student SET status = 0,update_time = sysdate() where id=#{id}")
    public int deleteStudent(int id);
    @Update("UPDATE homework_record SET status = 0,update_time = sysdate() where student_id=#{id}")
    public int deleteStudentHomeworkRecord(int id);
//    public int deleteStudent(int id);

    @Select("select * from student where id=#{id}")
    public Student getStudent(int id);

//    @Select("select g.grade,gc.classes,s.* from student s,grade g,grade_classes gc where s.grade_classes_id = gc.id and gc.grade_id = g.id")
    @SelectProvider(type = StudentSQLProvider.class,method = "getStudentList")
//    @Select("select g.grade,gc.classes,s.* from student s,grade g,grade_classes gc where s.`status` = 1 and s.grade_classes_id = gc.id and gc.grade_id = g.id order by gc.grade_id,gc.classes,s.seat_number")
    public List<Map> getStudentList(Integer gradeId,Integer classes);

}