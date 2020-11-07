package com.homework.teach.service;

import com.homework.teach.domain.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IMenuService {
    public int insertMenu(Menu menu);

    public int updateMenu(Menu menu);

    public int deleteMenu(int id);

    public Menu getMenu(int id);

    public List<Map> getMenuList();

    public List<Map> getParentMenu();
    public List<Map> getAdminParentMenu(int roleId);

    public List<Map> getSonMenu(String menuNo);
    public List<Map> getAdminSonMenu(String menuNo, int roleId);
}