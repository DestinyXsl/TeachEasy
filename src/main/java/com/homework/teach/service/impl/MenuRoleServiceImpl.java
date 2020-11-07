package com.homework.teach.service.impl;


import com.homework.teach.domain.MenuRole;
import com.homework.teach.mapper.MenuRoleMapper;
import com.homework.teach.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuRoleServiceImpl implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public int insertMenuRole(MenuRole menuRole) {
        return menuRoleMapper.insertMenuRole(menuRole);
    }

    @Override
    public int updateMenuRole(MenuRole menuRole) {
        return menuRoleMapper.updateMenuRole(menuRole);
    }

    @Override
    public int deleteMenuRole(int id) {
        return menuRoleMapper.deleteMenuRole(id);
    }

    @Override
    public MenuRole getMenuRole(int id) {
        return menuRoleMapper.getMenuRole(id);
    }

    @Override
    public int deleteMenuRoleByRoleId(int roleId) {
        return menuRoleMapper.deleteMenuRoleByRoleId(roleId);
    }
}