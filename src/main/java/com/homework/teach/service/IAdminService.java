package com.homework.teach.service;



import com.homework.teach.domain.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IAdminService {
    public int insertAdmin(Admin admin);

    public int updateAdmin(Admin admin);

    public int deleteAdmin(int id);

    public Admin getAdmin(int id);

    public List<Map> getAdminList(String accountOrName);

    public Admin adminLogin(String account, String password);
}