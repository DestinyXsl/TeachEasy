package com.homework.teach.mapper;

import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.Grade;

import java.util.List;
import java.util.Map;

@Mapper
public interface GradeMapper {

    @Insert("INSERT into grade (grade_num,grade,create_time,status) values (#{gradeNum},#{grade},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertGrade(Grade grade);

    @Update("UPDATE grade SET grade_num=#{gradeNum},grade=#{grade},create_time=#{createTime},status=#{status} where uuid=#{uuid}")
    public int updateGrade(Grade grade);

//    @Delete("delete from grade where id=#{id}")
    @Update("UPDATE grade SET status = 0 where id=#{id}")
    public int deleteGrade(int id);

    @Select("select * from grade where id=#{id}")
    public Grade getGrade(int id);

    @Select("select * from grade where status=1 order by grade_num")
    public List<Grade> getGradeList();

    @Select("select count(*) from grade g where (g.grade_num = #{gradeNum} or g.grade = #{grade}) and g.`status` = 1")
    public int existGrade(Grade grade);


}