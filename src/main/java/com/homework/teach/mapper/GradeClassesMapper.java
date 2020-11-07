package com.homework.teach.mapper;

import com.homework.teach.mapper.sqlProvide.AdminSQLProvider;
import com.homework.teach.mapper.sqlProvide.GradeClassesSQLProvider;
import org.apache.ibatis.annotations.*;
import com.homework.teach.domain.GradeClasses;

import java.util.List;
import java.util.Map;

@Mapper
public interface GradeClassesMapper {

    @Insert("INSERT into grade_classes (grade_id,classes,create_time,update_time,status) values (#{gradeId},#{classes},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertGradeClasses(GradeClasses gradeClasses);

    @Update("UPDATE grade_classes SET grade_id=#{gradeId},classes=#{classes},create_time=#{createTime},update_time=#{updateTime},status=#{status} where uuid=#{uuid}")
    public int updateGradeClasses(GradeClasses gradeClasses);

//    @Delete("delete from grade_classes where id=#{id}")
    @Update("update grade_classes set status=0 where id = #{id}")
    public int deleteGradeClasses(int id);

    @Select("select * from grade_classes where id=#{id}")
    public GradeClasses getGradeClasses(int id);

    @SelectProvider(type = GradeClassesSQLProvider.class,method = "getGradeClassesList")
    public List<Map> getGradeClassesList(Integer gradeId);

    @Select("select count(*) from grade_classes gc where gc.grade_id = #{gradeId} and gc.classes = #{classes} and gc.`status` = 1")
    public int getGradeClassesByGC(@Param("gradeId") int gradeId,@Param("classes") String classes);

    @Select("select count(*) from grade_classes gc where gc.grade_id = #{gradId} and gc.`status` = 1")
    public int getClassesCountByGradeId(int gradId);

    @Select("select count(*) from student s where s.grade_classes_id = #{classesId} and s.`status` = 1")
    public int existStudent(int classesId);
    @Select("select count(*) from homework h where h.grade_classes_id = #{classesId} and h.`status` = 1")
    public int existHomework(int classesId);
    @Select("select count(*) from work_book wb where wb.grade_classes_id = #{classesId} and wb.`status` = 1")
    public int existWorkBook(int classesId);


}