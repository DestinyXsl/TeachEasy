package com.homework.teach.mapper;

import com.homework.teach.domain.Student;
import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.WorkBook;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkBookMapper {

    @Insert("INSERT into work_book (grade_classes_id,subject_id,admin_id,name,create_time,update_time,status) values (#{gradeClassesId},#{subjectId},#{adminId},#{name},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertWorkBook(WorkBook workBook);

    @Update("UPDATE work_book SET grade_classes_id=#{gradeClassesId},subject_id=#{subjectId},admin_id=#{adminId},name=#{name},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateWorkBook(WorkBook workBook);
    @Update("UPDATE work_book SET base_tcode = #{baseTcode} where id=#{id}")
    public int updateWorkBookTcode(WorkBook workBook);

    @Select("select wb.base_tcode as baseTcode from work_book wb where wb.id = #{workBookId}")
    public String getBaseTcodeById(int workBookId);

//    @Delete("delete from work_book where id=#{id}")
//    public int deleteWorkBook(int id);

    @Update("UPDATE work_book SET status = 0,update_time = sysdate() where id=#{id}")
    public int deleteWorkBook(int id);
    @Select("select count(*) from homework h where h.work_book_id = #{id} and h.`status` = 1")
    public int existHomework(int id);

    @Select("select count(*) from work_book wb where wb.admin_id = #{adminId} and wb.grade_classes_id = #{gradeClassesId} and wb.subject_id = #{subjectId} and wb.name = #{name} and wb.`status` = 1")
    public int getWorkBookByName(WorkBook workBook);

    @Select("select * from work_book where id=#{id}")
    public WorkBook getWorkBook(int id);

//    @Select("select * from work_book where status = 1")
    @Select("select wk.id,g.grade,gc.classes,s.subject_name,wk.`name`,wk.create_time from work_book wk,grade_classes gc,grade g,`subject` s where wk.admin_id = #{adminId} and wk.grade_classes_id = gc.id and gc.grade_id = g.id and wk.subject_id = s.id and wk.`status` = 1 order by wk.create_time desc")
    public List<Map> getWorkBookList(int adminId);

    @Select("select s.* from student s where s.grade_classes_id = #{gradeClassesId} and s.`status` = 1 order by s.id")
    public List<Student> getStudentIdByWorkBook(int gradeClassesId);
    @Select("select concat('(',g.grade_num,'年',gc.classes,'班)(',s.subject_name,')') from work_book wb,grade g,grade_classes gc,`subject` s where wb.grade_classes_id = gc.id and gc.grade_id = g.id and wb.subject_id = s.id and wb.id = #{workBookId} and gc.`status` = 1 and s.`status` = 1")
    public String getTcodeCN(int workBookId);

    @Select("select concat(wb.base_tcode,lpad(s.id,5,0)) from work_book wb,student s where wb.admin_id = #{adminId} and wb.id = #{workBookId} and wb.grade_classes_id = s.grade_classes_id and wb.`status` = 1 and s.`status` = 1 order by s.id")
    public List<String> getTcodeByWorkBookId(@Param("adminId") int adminId,@Param("workBookId") int workBookId);

    @Select("select wb.id,wb.`name` from work_book wb where wb.admin_id = #{adminId} and wb.subject_id = #{subjectId} and wb.grade_classes_id = #{classesId} and wb.`status` = 1")
    public List<Map> getWorkBookListByAdminId(@Param("adminId") int adminId,@Param("subjectId") int subjectId,@Param("classesId") int classesId);

}