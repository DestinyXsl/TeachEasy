package com.homework.teach.mapper;



import com.homework.teach.domain.Admin;
import com.homework.teach.mapper.sqlProvide.AdminSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    @Insert("INSERT into admin (acc_status,account,password,name,role_id,create_time,update_time,status) values (#{accStatus},#{account},#{password},#{name},#{roleId},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertAdmin(Admin admin);

    @Update("UPDATE admin SET acc_status=#{accStatus},account=#{account},password=#{password},name=#{name},role_id=#{roleId},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateAdmin(Admin admin);

    @Delete("delete from admin where id=#{id}")
    public int deleteAdminTrue(int id);

    @Update("update admin set status=0,update_time = sysdate() where id=#{id}")
    public int deleteAdmin(int id);

    @Select("select * from admin where id=#{id} and status = 1")
    public Admin getAdmin(int id);

    @SelectProvider(type = AdminSQLProvider.class,method = "getAdminList")
    public List<Map> getAdminList(String accountOrName);

    @Select("select * from admin a where a.account = #{account} and a.password = #{password} and a.`status` = 1")
    public Admin adminLogin(@Param("account") String account, @Param("password") String password);

}