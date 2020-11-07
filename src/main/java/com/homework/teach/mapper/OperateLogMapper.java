package com.homework.teach.mapper;


import com.homework.teach.domain.OperateLog;
import com.homework.teach.mapper.sqlProvide.OperateLogSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    @Insert("INSERT into operate_log (admin_id,admin_name,menu_no,operate_title,create_time,status) values (#{adminId},#{adminName},#{menuNo},#{operateTitle},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertOperateLog(OperateLog operateLog);

    @Update("UPDATE operate_log SET admin_id=#{adminId},admin_name=#{adminName},menu_no=#{menuNo},operate_title=#{operateTitle},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateOperateLog(OperateLog operateLog);

    @Delete("delete from operate_log where id=#{id}")
    public int deleteOperateLog(int id);

    @Select("select * from operate_log where id=#{id}")
    public OperateLog getOperateLog(int id);

    @SelectProvider(type = OperateLogSQLProvider.class,method = "getOperateLogList")
    public List<OperateLog> getOperateLogList(String nameOrMenu, String startTime, String endTime);

}