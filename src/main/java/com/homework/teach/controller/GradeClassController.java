package com.homework.teach.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.*;
import com.homework.teach.service.*;
import com.homework.teach.util.CommonUtil;
import com.homework.teach.util.PageBean;
import com.homework.teach.util.RedisComponentUtil;
import com.homework.teach.util.constant.MessageConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/gradeClass")
public class GradeClassController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    public IGradeClassesService gradeClassesService;
    @Autowired
    public IGradeService gradeService;
    @Autowired
    private IOperateLogService operateLogService;
    /**
     *年级列表查询
     *管理后台查询年级列表接口
     */
    @ApiOperation(value = "年级列表查询", notes = "管理后台查询年级列表接口")
    @RequestMapping(value = "/getGradeList",method = RequestMethod.GET)
    public String getGradeList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
//        int gradeNum = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeNum"),"-500"));
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

        List<Grade> resultList = gradeService.getGradeList();
        PageBean<Grade> list = new PageBean<Grade>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        m.addAttribute("gradeList",list);
        m.addAttribute("params","");
        m.addAttribute("pageTitle","班级管理");
        setAdminMsg(m, request, session);
        return "gradeListPage";
    }

    /**
     *新增级
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertGrade")
    @ResponseBody
    public HashMap<String,Object> insertGrade(Grade g, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(g!=null){
                int exist = gradeService.existGrade(g);
                if(exist > 0){
                    return CommonUtil.ToResultHashMap(status,"年级序号或年级名称已存在!",data);
                }
                Date d = new Date();
                g.setCreateTime(d);
//                exists.setOperaterId();=======================================================
                g.setStatus(1);
                int result = gradeService.insertGrade(g);
                if (result == 1){
                    status = MessageConstant.SUCCESS_CODE;
                    message = MessageConstant.SUCCESS_INFO;
//                    insertLog(request,"K005","内容管理->轮播图列表->新增轮播图");
                }else{
                    throw new RuntimeException();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除级
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteGrade")
    @ResponseBody
    public HashMap<String,Object> deleteGrade(int gradeId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            int count = gradeClassesService.getClassesCountByGradeId(gradeId);
            if(count != 0){
                return CommonUtil.ToResultHashMap(status,"该年级存在班级数据，不能删除。",data);
            }
            int result = gradeService.deleteGrade(gradeId);
            if (result >= 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
//                insertLog(request,"K005","内容管理->轮播图列表->删除轮播图");
            }else{
                throw new RuntimeException();
            }

        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }



    /**
     *班级列表查询
     *管理后台查询班级列表接口
     */
    @ApiOperation(value = "班级列表查询", notes = "管理后台查询班级列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeId", value = "级Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getGradeClassList",method = RequestMethod.GET)
    public String getGradeClassList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int gradeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeId"),"-500"));

        List<Map> resultList = gradeClassesService.getGradeClassesList(gradeId);
        PageBean<Map> list = new PageBean<Map>(resultList);
        List<Grade> gradeList = gradeService.getGradeList();
//        return CommonUtil.ToResultHashMap(status,message,list);
        m.addAttribute("gradeClassesList",list);
        m.addAttribute("gradeList",gradeList);
        m.addAttribute("gradeId",gradeId);
        m.addAttribute("pageTitle","班级管理");
        if(gradeId != -500){
            m.addAttribute("params","&gradeId="+gradeId);
        }else{
            m.addAttribute("params","");
        }
        setAdminMsg(m, request, session);
        return "gradeClassListPage";
    }

    @ApiOperation(value = "根据级Id获取班列表", notes = "根据级Id获取班列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeId", value = "级Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getGradeClassListByGradeId",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getGradeClassListByGradeId(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int gradeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeId"),"-500"));
        List<Map> resultList = gradeClassesService.getGradeClassesList(gradeId);

//        m.addAttribute("gradeClassesList",resultList);
        if(resultList != null && resultList.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,resultList);
//        return "gradeClassListPage";
    }

    /**
     *新增班级
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertGradeClasses")
    @ResponseBody
    public HashMap<String,Object> insertGradeClasses(GradeClasses gc, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(gc!=null){

                int existClasses = gradeClassesService.getGradeClassesByGC(gc.getGradeId(),gc.getClasses());
                if (existClasses != 0){
                    return CommonUtil.ToResultHashMap(status,"该班级已存在!",data);
                }

                Date d = new Date();
                gc.setCreateTime(d);
                gc.setUpdateTime(d);
//                exists.setOperaterId();=======================================================
                gc.setStatus(1);
                int result = gradeClassesService.insertGradeClasses(gc);
                if (result == 1){
                    status = MessageConstant.SUCCESS_CODE;
                    message = MessageConstant.SUCCESS_INFO;
//                    insertLog(request,"K005","内容管理->轮播图列表->新增轮播图");
                }else{
                    throw new RuntimeException();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *删除年级
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteGradeClasses")
    @ResponseBody
    public HashMap<String,Object> deleteGradeClasses(int gradeClassesId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            //学生
            int exist = gradeClassesService.existStudent(gradeClassesId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该班级存在学生数据，不能删除。",data);
            }
            //作业
            exist = gradeClassesService.existHomework(gradeClassesId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该班级存在作业数据，不能删除。",data);
            }
            //作业本
            exist = gradeClassesService.existWorkBook(gradeClassesId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该班级存在作业本数据，不能删除。",data);
            }

            int result = gradeClassesService.deleteGradeClasses(gradeClassesId);
            if (result >= 1){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
//                insertLog(request,"K005","内容管理->轮播图列表->删除轮播图");
            }else{
                throw new RuntimeException();
            }

        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return CommonUtil.ToResultHashMap(status,message,data);
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
}
