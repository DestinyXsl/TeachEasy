package com.homework.teach.service.impl;


import com.homework.teach.domain.Menu;
import com.homework.teach.mapper.MenuMapper;
import com.homework.teach.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int insertMenu(Menu menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenu(int id) {
        return menuMapper.deleteMenu(id);
    }

    @Override
    public Menu getMenu(int id) {
        return menuMapper.getMenu(id);
    }

    @Override
    public List<Map> getMenuList() {
        return menuMapper.getMenuList();
    }

    @Override
    public List<Map> getParentMenu() {
        return menuMapper.getParentMenu();
    }

    @Override
    public List<Map> getAdminParentMenu(int roleId) {
        return menuMapper.getAdminParentMenu(roleId);
    }

    @Override
    public List<Map> getSonMenu(String menuNo) {
        return menuMapper.getSonMenu(menuNo);
    }

    @Override
    public List<Map> getAdminSonMenu(String menuNo,int roleId) {
        return menuMapper.getAdminSonMenu(menuNo, roleId);
    }
}