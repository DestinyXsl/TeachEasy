package com.homework.teach.service;



import com.homework.teach.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IRoleService {
    public int insertRole(Role role);

    public int updateRole(Role role);

    public boolean deleteRole(int id);

    public Role getRole(int id);

    public List<Map> getRoleList();

    public Map getRoleMenuMsgById(int roleId);

    public List<Map> getRoleDataMsg();
}