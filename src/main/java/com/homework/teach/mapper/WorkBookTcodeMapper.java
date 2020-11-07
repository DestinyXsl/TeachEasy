package com.homework.teach.mapper;

import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.WorkBookTcode;

@Mapper
public interface WorkBookTcodeMapper {

    @Insert("INSERT into work_book_tcode (work_book_id,student_id,tcode,description,create_time,is_delete) values (#{workBookId},#{studentId},#{tcode},#{description},#{createTime},#{isDelete})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertWorkBookTcode(WorkBookTcode workBookTcode);

    @Update("UPDATE work_book_tcode SET work_book_id=#{workBookId},student_id=#{studentId},tcode=#{tcode},description=#{description},create_time=#{createTime},is_delete=#{isDelete} where uuid=#{uuid}")
    public int updateWorkBookTcode(WorkBookTcode workBookTcode);

    @Delete("delete from work_book_tcode where id=#{id}")
    public int deleteWorkBookTcode(int id);

    @Select("select * from work_book_tcode where id=#{id}")
    public WorkBookTcode getWorkBookTcode(int id);

}