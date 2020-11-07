package com.homework.teach.mapper;

import com.homework.teach.domain.Student;
import com.homework.teach.mapper.sqlProvide.HomeworkRecordSQLProvider;
import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.Homework;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeworkMapper {

    @Insert("INSERT into homework (grade_classes_id,subject_id,admin_id,work_book_id,content,homework_status,create_time,update_time,status) values (#{gradeClassesId},#{subjectId},#{adminId},#{workBookId},#{content},#{homeworkStatus},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertHomework(Homework homework);

    @Update("UPDATE homework SET grade_classes_id=#{gradeClassesId},subject_id=#{subjectId},admin_id=#{adminId},work_book_id=#{workBookId},content=#{content},homework_status=#{homeworkStatus},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateHomework(Homework homework);

//    @Delete("delete from homework where id=#{id}")
//    public int deleteHomework(int id);
    @Update("UPDATE homework set status = 0,update_time = sysdate() where id=#{id}")
    public int deleteHomework(int id);
    @Update("UPDATE homework_record SET status = 0,update_time = sysdate() where home_work_id=#{id}")
    public int deleteHomeworkRecord(int id);

    @Select("select * from homework where id=#{id}")
    public Homework getHomework(int id);
    @Select("select * from homework h where h.`status` = 1 and h.id = #{homeworkId}")
    public int getHomeworkStatus(int homeworkId);

    @Select("select h.id,g.grade,gc.classes,s.subject_name,wb.`name`,h.content,h.homework_status,h.create_time from homework h,grade g,grade_classes gc,`subject` s,work_book wb where h.admin_id = #{adminId} and h.grade_classes_id = gc.id and gc.grade_id = g.id and h.subject_id = s.id and h.work_book_id = wb.id and gc.`status` = 1 and h.`status` = 1 and wb.`status` = 1 order by h.create_time desc")
    public List<Map> getHomeworkList(int adminId);

    @Select("select s.id,s.seat_number from homework h,student s where h.grade_classes_id = s.grade_classes_id and h.id = #{homeworkId} and h.`status` = 1 and s.`status` = 1")
    public List<Student> getStudentIdByHomework(int homeworkId);

//    insert into homework_record (home_work_id,student_id,record_status,create_time,update_time,`status`) values (1,2,0,sysdate(),sysdate(),1),(1,3,0,sysdate(),sysdate(),1),(1,4,0,sysdate(),sysdate(),1),(1,5,0,sysdate(),sysdate(),1),(1,6,0,sysdate(),sysdate(),1);
//    public List<Map> getHomeworkList();

//    @Select("SELECT concat(g.grade ,'(', gc.classes,')班') as grade_class ,s.subject_name,h.grade_classes_id,h.subject_id FROM homework h, grade_classes gc, `subject` s,grade g WHERE gc.grade_id = g.id and h.admin_id = #{adminId} AND s.id = h.subject_id AND gc.id = h.grade_classes_id AND gc.`status` = 1 AND h.`status` = 1 AND s.`status` = 1 GROUP BY s.id, gc.id")
    @Select("select (@i:=@i+1) uid,a.* from (SELECT concat(g.grade ,'(', gc.classes,')班') as grade_class ,s.subject_name,h.grade_classes_id,h.subject_id FROM homework h, grade_classes gc, `subject` s,grade g WHERE gc.grade_id = g.id and h.admin_id = #{adminId} AND s.id = h.subject_id AND gc.id = h.grade_classes_id AND gc.`status` = 1 AND h.`status` = 1 AND s.`status` = 1 GROUP BY s.id, gc.id) a, (SELECT @i:=0) AS utable")
    public List<Map> getHomeworkStatisticalList(int adminId);

    @Select("select a.*,ifnull(b.weijiao,'') as weijiao,ifnull(c.you,'') as you,ifnull(d.cuowuduo,'') as cuowuduo,ifnull(e.weiwancheng,'') as weiwancheng from " +
            "(select s.id,s.`name`,count(*) as sum from homework h,homework_record hr,student s where h.grade_classes_id = #{gradeClassesId} and h.subject_id = #{subjectId} and hr.student_id = s.id and h.id = hr.home_work_id and h.`status` = 1 and hr.`status` = 1 group by s.id) a " +
            "left join (select s.id,s.`name`,count(*) as weijiao from homework h,homework_record hr,student s where h.grade_classes_id = #{gradeClassesId} and h.subject_id = #{subjectId} and hr.student_id = s.id and h.id = hr.home_work_id and h.`status` = 1 and hr.`status` = 1 and hr.record_status = 0 group by s.id) b on a.id = b.id " +
            "left join (select s.id,s.`name`,count(*) as you from homework h,homework_record hr,student s where h.grade_classes_id = #{gradeClassesId} and h.subject_id = #{subjectId} and hr.student_id = s.id and h.id = hr.home_work_id and h.`status` = 1 and hr.`status` = 1 and hr.record_status = 1 and (hr.`level` = 0 or hr.`level` = 1) group by s.id) c on a.id = c.id " +
            "left join (select s.id,s.`name`,count(*) as cuowuduo from homework h,homework_record hr,student s where h.grade_classes_id = #{gradeClassesId} and h.subject_id = #{subjectId} and hr.student_id = s.id and h.id = hr.home_work_id and h.`status` = 1 and hr.`status` = 1 and hr.record_status = 1 and hr.`level` = 2 group by s.id) d on a.id = d.id " +
            "left join (select s.id,s.`name`,count(*) as weiwancheng from homework h,homework_record hr,student s where h.grade_classes_id = #{gradeClassesId} and h.subject_id = #{subjectId} and hr.student_id = s.id and h.id = hr.home_work_id and h.`status` = 1 and hr.`status` = 1 and hr.record_status = 1 and hr.`level` = 3 group by s.id) e on a.id = e.id")
    public List<Map> getChart(@Param("gradeClassesId") int gradeClassesId, @Param("subjectId") int subjectId);

}