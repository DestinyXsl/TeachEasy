package com.homework.teach.mapper;



import com.homework.teach.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    @Insert("INSERT into role (title,create_time,update_time,status) values (#{title},#{createTime},#{updateTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertRole(Role role);

    @Update("UPDATE role SET title=#{title},create_time=#{createTime},update_time=#{updateTime},status=#{status} where id=#{id}")
    public int updateRole(Role role);

//    @Delete("delete from role where id=#{id}")
//    public int deleteRole(int id);

    @Select("select * from role where id=#{id}")
    public Role getRole(int id);

    @Select("select r.id,r.title as roleTitle,GROUP_CONCAT(m.title) as managerModel from menu_role mr,role r,menu m where r.`status` = 1 and mr.`status` = 1 and r.id = mr.role_id and mr.menu_id = m.id group by r.title order by r.create_time desc")
    public List<Map> getRoleList();

    @Select("select r.title,group_concat(m.id) as menus from menu_role mr,menu m,role r where r.id = mr.role_id and mr.menu_id = m.id and mr.role_id = #{roleId} and mr.`status` = 1 group by r.title")
    public Map getRoleMenuMsgById(int roleId);

    @Update("update role set `status` = 0 and update_time = sysdate() where id = #{roleId}")
    public int deleteRole(int id);

    @Select("select r.id,r.title from role r where r.`status` = 1")
    public List<Map> getRoleDataMsg();

}