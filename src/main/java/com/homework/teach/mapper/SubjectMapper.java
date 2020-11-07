package com.homework.teach.mapper;

import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.Subject;

import java.util.List;

@Mapper
public interface SubjectMapper {

    @Insert("INSERT into subject (subject_name,create_time,status) values (#{subjectName},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertSubject(Subject subject);

    @Update("UPDATE subject SET subject_name=#{subjectName},create_time=#{createTime},status=#{status} where uuid=#{uuid}")
    public int updateSubject(Subject subject);

//    @Delete("delete from subject where id=#{id}")
//    public int deleteSubject(int id);

    @Update("UPDATE subject SET status = 0 where id=#{id}")
    public int deleteSubject(int id);
    @Select("select count(*) from homework h where h.subject_id = #{id} and h.`status` = 1")
    public int existHomework(int id);
    @Select("select count(*) from work_book wb where wb.subject_id = #{id} and wb.`status` = 1")
    public int existWorkBook(int id);

    @Select("select count(*) from subject s where s.subject_name = #{subjectName} and s.`status` = 1")
    public int existSubject(Subject subject);
    @Select("select * from subject where id=#{id}")
    public Subject getSubject(int id);

    @Select("select * from subject where status = 1")
    public List<Subject> getSubjectList();

}