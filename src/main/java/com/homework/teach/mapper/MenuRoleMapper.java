package com.homework.teach.mapper;



import com.homework.teach.domain.MenuRole;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MenuRoleMapper {

    @Insert("INSERT into menu_role (role_id,menu_id,create_time,status) values (#{roleId},#{menuId},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertMenuRole(MenuRole menuRole);

    @Update("UPDATE menu_role SET role_id=#{roleId},menu_id=#{menuId},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateMenuRole(MenuRole menuRole);

//    @Delete("delete from menu_role where id=#{id}")
//    public int deleteMenuRole(int id);

    @Select("select * from menu_role where id=#{id}")
    public MenuRole getMenuRole(int id);

    @Update("update menu_role set `status` = 0 where role_id = #{roleId}")
    public int deleteMenuRoleByRoleId(int roleId);

    @Update("update menu_role set `status` = 0 where role_id = #{roleId}")
    public int deleteMenuRole(int roleId);

}