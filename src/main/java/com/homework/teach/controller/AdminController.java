package com.homework.teach.controller;

import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.*;
import com.homework.teach.service.*;
import com.homework.teach.util.CommonUtil;
import com.homework.teach.util.PageBean;
import com.homework.teach.util.RedisComponentUtil;

import com.homework.teach.util.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuRoleService menuRoleService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    private IOperateLogService operateLogService;
    /**
     *新增菜单数据
     *手动插入菜单方法
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertMenu", method = RequestMethod.POST)
    public HashMap<String,Object> insertMenu(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
        String menuNo = CommonUtil.getStr(request.getParameter("menuNo"),"");
        if(menuNo == null || menuNo.equals("")){return CommonUtil.ToResultHashMap(status,"menuNo为空!",null);}
        String parentMenuNo = CommonUtil.getStr(request.getParameter("parentMenuNo"),"");
//        if(parentMenuNo == null || parentMenuNo.equals("")){return CommonUtil.ToResultHashMap(status,"parentMenuNo为空!",null);}
        //菜单级别	菜单级别(1父,,,2子)
        int type = Integer.parseInt(CommonUtil.getStr(request.getParameter("type"),"-500"));
        if(type == -500){return CommonUtil.ToResultHashMap(status,"type为空!",null);}

        try {
            Menu m = new Menu();
            m.setTitle(title);
            m.setMenuNo(menuNo);
            if(!parentMenuNo.equals("")){
                m.setParentMenuNo(parentMenuNo);
            }
            m.setType(type);
            m.setCreateTime(new Date());
            m.setStatus(1);
            int result = menuService.insertMenu(m);
            if (result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }else{
                throw new RuntimeException();
            }

        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增菜单数据异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *删除管理员账号
     *根据Id删除管理员
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
    public HashMap<String,Object> deleteAdmin(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        try {
            int result = adminService.deleteAdmin(id);
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
                insertLog(request,"K012","权限管理->管理员列表->删除管理员");
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除管理员账号异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    /**
     *身份列表查询
     *管理后台获取管理员身份列表接口
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public String getRoleList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        //管理员身份标题	模糊查询管理员身份数据
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        List resultList = roleService.getRoleList();

        PageBean<Map> list = new PageBean<Map>(resultList);
//        Map roleMsg = new HashMap();
//        roleMsg.put("id",1);
//        roleMsg.put("roleTitle","市场部管理员");
//        roleMsg.put("managerModel","用户管理/店场景管理");
//        resultList.add(roleMsg);
        m.addAttribute("roleList",list);
        m.addAttribute("pageTitle","权限管理");
        setAdminMsg(m, request, session);
        return "roleListPage";
    }

    /**
     *获取身份键值对信息
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleDataMsg", method = RequestMethod.GET)
    public HashMap<String,Object> getRoleDataMsg(Model m,HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        List resultList = roleService.getRoleDataMsg();
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,resultList);
    }
    /**
     *身份列表查询
     *管理后台获取管理员身份列表接口
     */
    @ResponseBody
    @RequestMapping(value = "/getAdminMenu", method = RequestMethod.GET)
    public HashMap<String,Object> getAdminMenu(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        //管理员身份标题	模糊查询管理员身份数据
        String title = CommonUtil.getStr(request.getParameter("title"),"");

        HttpSession session  = request.getSession();
        Admin a = (Admin)redisComponentUtil.get(session.getId());
        a.getRoleId();
        //select m.id,m.title,m.menu_no as menuNo from menu_role mr,menu m where m.type = 1 and mr.menu_id = m.id and mr.`status` = 1 and mr.role_id = ?
        List<Map> resultList = menuService.getAdminParentMenu(a.getRoleId());
        for (Map pMenu : resultList){
            //select m.id,m.title from menu m,menu_role mr where mr.menu_id = m.id and m.`status` = 1 and m.type = 2 and m.parent_menu_no = ? and mr.role_id = ? and mr.`status` = 1
            pMenu.put("children",menuService.getAdminSonMenu(CommonUtil.getStr(pMenu.get("menuNo"),""),a.getRoleId()));
        }

//        Map m = new HashMap();
//        m.put("id",1);
//        m.put("title","用户管理");
//        List<Map> subList = new ArrayList<Map>();
//        Map subM = new HashMap();
//        subM.put("id",2);
//        subM.put("title","用户列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",3);
//        m.put("title","场景管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",4);
//        subM.put("title","场景列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",5);
//        subM.put("title","场景审核列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",6);
//        m.put("title","需求管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",7);
//        subM.put("title","需求列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",8);
//        m.put("title","内容管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",9);
//        subM.put("title","轮播图列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",10);
//        subM.put("title","剧组服务列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",11);
//        subM.put("title","反馈信息");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//        PageBean<Map> list = new PageBean<Map>(resultList);
//        Map roleMsg = new HashMap();
//        roleMsg.put("id",1);
//        roleMsg.put("roleTitle","市场部管理员");
//        roleMsg.put("managerModel","用户管理/店场景管理");
//        resultList.add(roleMsg);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;

        return CommonUtil.ToResultHashMap(status,message,resultList);
    }
    /**
     *身份列表查询
     *管理后台获取管理员身份列表接口
     */
    @ResponseBody
    @RequestMapping(value = "/getModelList", method = RequestMethod.GET)
    public HashMap<String,Object> getModelList(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        //管理员身份标题	模糊查询管理员身份数据
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        List<Map> resultList = menuService.getParentMenu();
        for (Map pMenu : resultList){
            pMenu.put("children",menuService.getSonMenu(CommonUtil.getStr(pMenu.get("menuNo"),"")));
        }

//        Map m = new HashMap();
//        m.put("id",1);
//        m.put("title","用户管理");
//        List<Map> subList = new ArrayList<Map>();
//        Map subM = new HashMap();
//        subM.put("id",2);
//        subM.put("title","用户列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",3);
//        m.put("title","场景管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",4);
//        subM.put("title","场景列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",5);
//        subM.put("title","场景审核列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",6);
//        m.put("title","需求管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",7);
//        subM.put("title","需求列表");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//
//        m = new HashMap();
//        m.put("id",8);
//        m.put("title","内容管理");
//        subList = new ArrayList<Map>();
//        subM = new HashMap();
//        subM.put("id",9);
//        subM.put("title","轮播图列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",10);
//        subM.put("title","剧组服务列表");
//        subList.add(subM);
//        subM = new HashMap();
//        subM.put("id",11);
//        subM.put("title","反馈信息");
//        subList.add(subM);
//        m.put("children",subList);
//        resultList.add(m);
//        PageBean<Map> list = new PageBean<Map>(resultList);
//        Map roleMsg = new HashMap();
//        roleMsg.put("id",1);
//        roleMsg.put("roleTitle","市场部管理员");
//        roleMsg.put("managerModel","用户管理/店场景管理");
//        resultList.add(roleMsg);
        status = MessageConstant.SUCCESS_CODE;
        message = MessageConstant.SUCCESS_INFO;

        return CommonUtil.ToResultHashMap(status,message,resultList);
    }
    /**
     *新增&编辑身份
     *管理后台新增$编辑后保存管理员角色身份接口
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/createAndUpdateRole", method = RequestMethod.POST)
    public HashMap<String,Object> createAndUpdateRole(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"0"));//id不为空时,,更新角色
        String title = CommonUtil.getStr(request.getParameter("title"),"");
        if(title == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"title为空!",null);}
        String moduleList = request.getParameter("moduleList");
        if(moduleList == null || title.equals("")){return CommonUtil.ToResultHashMap(status,"moduleList为空!",null);}
        try {
            Date d = new Date();
            Role r = new Role();
            if(id != 0){
                r = roleService.getRole(id);
            }
            r.setTitle(title);
            if (id != 0){
                r.setUpdateTime(d);
            }else{
                r.setCreateTime(d);
                r.setUpdateTime(d);
            }
            r.setStatus(1);
            int result = 0;
            if(id != 0){
                result = roleService.updateRole(r);
                insertLog(request,"K013","权限管理->身份列表->编辑身份");
            }else{
                result = roleService.insertRole(r);
                insertLog(request,"K013","权限管理->身份列表->新增身份");
            }
            if(result == 1){
                if(id != 0){
                    menuRoleService.deleteMenuRoleByRoleId(id);
                }
                String[] modules = moduleList.split(",");
                for (String m : modules){
                    MenuRole mr = new MenuRole();
                    mr.setMenuId(Integer.parseInt(m));
                    mr.setRoleId(r.getId());
                    mr.setCreateTime(d);
                    mr.setStatus(1);
                    menuRoleService.insertMenuRole(mr);
                }
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
            System.out.println(title);
            System.out.println(moduleList);
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增身份异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,null);
    }

    /**
     *获取身份信息
     *获取身份信息(编辑用)
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleMsgById", method = RequestMethod.GET)
    public HashMap<String,Object> getRoleMsgById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}

        Map roleMsg = roleService.getRoleMenuMsgById(id);
        if(roleMsg != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,roleMsg);
    }
    /**
     *删除身份
     *根据Id删除身份角色
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteRoleById", method = RequestMethod.POST)
    public HashMap<String,Object> deleteRoleById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        try {
            boolean result = roleService.deleteRole(id);
            if(result){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
                insertLog(request,"K013","权限管理->管理员列表->删除身份");
            }else{
                throw new RuntimeException();
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("删除身份异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,null);
    }

    /**
     *操作日志查询
     *管理后台查询操作记录接口
     */
    @RequestMapping(value = "/getOperateLogList", method = RequestMethod.GET)
    public String getOperateLogList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String nameOrMenu = CommonUtil.getStr(request.getParameter("nameOrMenu"),"");
        //起始时间	格式(yyyy-MM-dd)
        String startTime = CommonUtil.getStr(request.getParameter("startTime"),"");
        //截止时间	格式(yyyy-MM-dd)
        String endTime = CommonUtil.getStr(request.getParameter("endTime"),"");
        List<OperateLog> resultList = operateLogService.getOperateLogList(nameOrMenu,startTime,endTime);
        if(resultList != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        PageBean<OperateLog> list = new PageBean<OperateLog>(resultList);
        m.addAttribute("logList",list);
        m.addAttribute("nameOrMenu",nameOrMenu);
        m.addAttribute("startTime",startTime);
        m.addAttribute("endTime",endTime);
        m.addAttribute("pageTitle","权限管理");
        setAdminMsg(m, request, session);
        return "logListPage";
    }
    public void setAdminMsg(Model m, HttpServletRequest request,HttpSession session){
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        m.addAttribute("menu",adminMsg.get("menu"));
//        Cookie[] cookies = request.getCookies();
//        String cookieValue = null;
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                System.out.println("cookie::"+cookie.getName()+"::::"+cookie.getValue());
//            }
//        }
    }
    public void insertLog(HttpServletRequest request,String menuNo,String operateTitle){
        HttpSession session  = request.getSession();
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        OperateLog ol = new OperateLog();
        ol.setAdminId(a.getId());
        ol.setAdminName(a.getName());
        ol.setCreateTime(new Date());
        ol.setMenuNo(menuNo);
        ol.setOperateTitle(operateTitle);
        ol.setStatus(1);
        operateLogService.insertOperateLog(ol);
    }

    /**
     *管理员列表查询
     *管理后台查询管理员列表
     */
    @RequestMapping(value = "/getAdminList")
    public String getAdminList(Model m, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        String accountOrName = CommonUtil.getStr(request.getParameter("accountOrName"),"");
        List<Map> resultList = adminService.getAdminList(accountOrName);
        PageBean<Map> list = new PageBean<Map>(resultList);
        m.addAttribute("adminList",list);
        m.addAttribute("accountOrName",accountOrName);
        m.addAttribute("pageTitle","权限管理");
        setAdminMsg(m, request, session);
//        session.setAttribute("auth_token","abcde");
//        String token = session.getAttribute("user").toString();


//        Admin a = (Admin) adminMsg.get("admin");
//        System.out.println("menu:::"+adminMsg.get("menu").toString());



//        System.out.println("Admin::"+a.toString());
//        System.out.println("tokenId::"+session.getId());
//        System.out.println("token::"+token);




        return "newPage";
//        return CommonUtil.ToResultHashMap(status,message,list);
    }
    /**
     *新增&编辑管理员
     *新增或修改管理员提交时调用接口
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping(value = "/createAndUpdateAdmin")
    public HashMap<String,Object> createAndUpdateAdmin(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        String account = CommonUtil.getStr(request.getParameter("account"),"");
        if(account == null || account.equals("")){return CommonUtil.ToResultHashMap(status,"account为空!",null);}
        String name = CommonUtil.getStr(request.getParameter("name"),"");
        if(name == null || name.equals("")){return CommonUtil.ToResultHashMap(status,"name为空!",null);}
        String password = CommonUtil.getStr(request.getParameter("password"),"");
        if(password == null || password.equals("")){return CommonUtil.ToResultHashMap(status,"password为空!",null);}
        int roleId = Integer.parseInt(CommonUtil.getStr(request.getParameter("roleId"),"-500"));
        if(roleId == -500){return CommonUtil.ToResultHashMap(status,"roleId为空!",null);}
        try {
            Admin a = new Admin();
            if (id != -500){
                a = adminService.getAdmin(id);
            }
            a.setAccStatus(1);
            a.setAccount(account);
            a.setName(name);
            a.setPassword(password);
            a.setRoleId(roleId);
            a.setCreateTime(new Date());
            a.setUpdateTime(new Date());
            a.setStatus(1);
            int result = 0;
            if(id != -500){
                result = adminService.updateAdmin(a);
                insertLog(request,"K012","权限管理->管理员列表->编辑管理员");
            }else{
                result = adminService.insertAdmin(a);
                insertLog(request,"K012","权限管理->管理员列表->新增管理员");
            }
            if(result == 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
            }
        } catch (Exception e){
            e.printStackTrace();
//            logger.error("新增&编辑管理员异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *根据Id获取管理员信息
     *获取管理员信息(编辑用)
     */
    @ResponseBody
    @RequestMapping(value = "/getAdminMsgById")
    public HashMap<String,Object> getAdminMsgById(HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int id = Integer.parseInt(CommonUtil.getStr(request.getParameter("id"),"-500"));
        if(id == -500){return CommonUtil.ToResultHashMap(status,"id为空!",null);}
        Admin a = adminService.getAdmin(id);
        if(a != null){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,a);
    }


}
