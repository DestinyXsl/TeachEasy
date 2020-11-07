package com.homework.teach.service;



import com.homework.teach.domain.MenuRole;
import org.springframework.stereotype.Service;

@Service
public interface IMenuRoleService {
    public int insertMenuRole(MenuRole menuRole);

    public int updateMenuRole(MenuRole menuRole);

    public int deleteMenuRole(int id);

    public MenuRole getMenuRole(int id);

    public int deleteMenuRoleByRoleId(int roleId);
}