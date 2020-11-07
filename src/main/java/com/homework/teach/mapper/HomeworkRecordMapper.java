package com.homework.teach.mapper;

import com.homework.teach.domain.Student;
import com.homework.teach.domain.vo.HomeworkRecordVo;
import com.homework.teach.domain.vo.UpdateHomeworkRecordVo;
import com.homework.teach.mapper.sqlProvide.HomeworkRecordSQLProvider;
import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.HomeworkRecord;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeworkRecordMapper {

//    @Insert("INSERT into homework_record (home_work_id,student_id,record_status,create_time,update_time,status) values (#{homeWorkId},#{studentId},#{recordStatus},#{createTime},#{updateTime},#{status})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    public int insertHomeworkRecord(HomeworkRecord homeworkRecord);

    @Update("UPDATE homework_record SET home_work_id=#{homeWorkId},student_id=#{studentId},tcode=#{tcode},record_status=#{recordStatus},level=#{level},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateHomeworkRecord(HomeworkRecordVo homeworkRecord);

    @Update("UPDATE homework_record SET record_status=#{recordStatus},update_time=sysdate() where id=#{id}")
    public int updateHomeworkRecordStatus(UpdateHomeworkRecordVo vo);
    @Update("UPDATE homework_record SET level=#{level},update_time=sysdate() where id=#{id}")
    public int updateHomeworkRecordLevel(UpdateHomeworkRecordVo vo);

    @Delete("delete from homework_record where id=#{id}")
    public int deleteHomeworkRecord(int id);

    @Select("select * from homework_record where id=#{id} and status = 1")
    public HomeworkRecord getHomeworkRecord(int id);

    @Select("select hr.id,hr.tcode,s.seat_number,s.`name`,hr.record_status,hr.level from homework_record hr,student s where hr.student_id = s.id and hr.home_work_id = #{homeworkId} and hr.`status` = 1 and s.`status` = 1 order by s.seat_number")
    public List<Map> getStudentHomeworkRecordList(int homeworkId);

    @InsertProvider(type = HomeworkRecordSQLProvider.class,method = "insertHomeworkRecord")
    public int insertHomeworkRecord(int homeworkId, List<Student> students, String tcode);

    @Select("select hr.*,a.account as accountName from homework h,homework_record hr,admin a where a.id = h.admin_id and h.homework_status = 2 and h.id = hr.home_work_id and hr.record_status = 0 and h.`status` = 1 and hr.`status` = 1 and hr.tcode = #{tcode} limit 1")
    public HomeworkRecordVo getHomeworkRecordByTCode(String tcode);

    @Select("select hr.*,a.account as accountName from homework h,homework_record hr,admin a where a.id = h.admin_id and h.homework_status = 2 and h.id = hr.home_work_id and hr.record_status = 0 and h.`status` = 1 and hr.`status` = 1 and hr.home_work_id = #{homeworkId} and hr.tcode = #{tcode} limit 1")
    public HomeworkRecordVo getHomeworkRecordByHWIdAndTCode(@Param("homeworkId") String homeworkId,@Param("tcode") String tcode);

}