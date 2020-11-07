package com.homework.teach.mapper.sqlProvide;

public class OperateLogSQLProvider {
    public String getOperateLogList(String nameOrMenu,String startTime,String endTime){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from operate_log ol where 1=1 ");
        if(!nameOrMenu.equals("")){
            sql.append(" and (ol.admin_name like '%"+nameOrMenu+"%' or ol.operate_title like '%"+nameOrMenu+"%')");
        }
        if(!startTime.equals("")){
            sql.append(" and ol.create_time > str_to_date('"+startTime+"','%Y-%m-%d')");
        }
        if(!endTime.equals("")){
            sql.append(" and ol.create_time < str_to_date('"+endTime+"','%Y-%m-%d')");
        }
        sql.append(" order by ol.create_time desc");
        System.out.println("+++++"+sql.toString());
        return sql.toString();
    }
}
