package com.homework.teach.service.impl;



import com.homework.teach.domain.Role;
import com.homework.teach.mapper.MenuRoleMapper;
import com.homework.teach.mapper.RoleMapper;
import com.homework.teach.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public boolean deleteRole(int id) {
        int result = roleMapper.deleteRole(id);
        if(result != 0){
            result = menuRoleMapper.deleteMenuRole(id);
        }
        if (result != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Role getRole(int id) {
        return roleMapper.getRole(id);
    }

    @Override
    public List<Map> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public Map getRoleMenuMsgById(int roleId) {
        return roleMapper.getRoleMenuMsgById(roleId);
    }

    @Override
    public List<Map> getRoleDataMsg() {
        return roleMapper.getRoleDataMsg();
    }
}