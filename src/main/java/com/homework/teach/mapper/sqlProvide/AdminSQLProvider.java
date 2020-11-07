package com.homework.teach.mapper.sqlProvide;


import org.apache.commons.lang.StringUtils;

public class AdminSQLProvider {
    public String getAdminList(String accountOrName){
        StringBuffer sql = new StringBuffer();
        sql.append("select a.id,a.account,a.name,ifnull(r.title,'无身份') as title from admin a left join role r on a.role_id = r.id and r.`status` = 1 where a.acc_status = 1 and a.`status` = 1");
        if(StringUtils.isNotBlank(accountOrName)){
            sql.append(" and (a.account like '%"+accountOrName+"%' or a.name like '%"+accountOrName+"%')");
        }
        sql.append(" order by a.create_time desc,a.id desc");
        return sql.toString();
    }
}
