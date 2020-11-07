package com.homework.teach.mapper;



import com.homework.teach.domain.Menu;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    @Insert("INSERT into menu (title,menu_no,parent_menu_no,type,url_path,create_time,status) values (#{title},#{menuNo},#{parentMenuNo},#{type},#{urlPath},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertMenu(Menu menu);

    @Update("UPDATE menu SET title=#{title},menu_no=#{menuNo},parent_menu_no=#{parentMenuNo},type=#{type},url_path=#{urlPath},create_time=#{createTime},status=#{status} where id=#{id}")
    public int updateMenu(Menu menu);

    @Delete("delete from menu where id=#{id}")
    public int deleteMenu(int id);

    @Select("select * from menu where id=#{id}")
    public Menu getMenu(int id);

    @Select("select * from menu ")
    public List<Map> getMenuList();

    @Select("select m.id,m.title,m.menu_no as menuNo from menu m where m.type = 1 and m.`status` = 1")
    public List<Map> getParentMenu();
    @Select("select m.id,m.title,m.menu_no as menuNo from menu_role mr,menu m where m.type = 1 and mr.menu_id = m.id and mr.`status` = 1 and mr.role_id = #{roleId}")
    public List<Map> getAdminParentMenu(int roleId);

    @Select("select m.id,m.title from menu m where m.`status` = 1 and m.type = 2 and m.parent_menu_no = #{menuNo}")
    public List<Map> getSonMenu(String menuNo);
    @Select("select m.id,m.title,ifnull(m.url_path,'#') as urlPath from menu m,menu_role mr where mr.menu_id = m.id and m.`status` = 1 and m.type = 2 and m.parent_menu_no = #{menuNo} and mr.role_id = #{roleId} and mr.`status` = 1")
    public List<Map> getAdminSonMenu(@Param("menuNo") String menuNo, @Param("roleId") int roleId);

}