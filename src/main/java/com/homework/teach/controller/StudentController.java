package com.homework.teach.controller;

import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.Grade;
import com.homework.teach.domain.GradeClasses;
import com.homework.teach.domain.Student;
import com.homework.teach.service.*;
import com.homework.teach.util.CommonUtil;
import com.homework.teach.util.PageBean;
import com.homework.teach.util.RedisComponentUtil;
import com.homework.teach.util.constant.MessageConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    public IGradeClassesService gradeClassesService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IStudentService studentService;

    /**
     *学生列表查询
     *管理后台查询学生列表接口
     */
    @ApiOperation(value = "学生列表查询", notes = "管理后台查询学生列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeId", value = "年级", paramType = "body", required = false, dataType = "int"),
            @ApiImplicitParam(name = "classes", value = "班", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getStudentList",method = RequestMethod.GET)
    public String getGradeClassList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int gradeId = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeId"),"-500"));
        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
        List<Map> resultList = studentService.getStudentList(gradeId,classes);
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);

        StringBuffer sb = new StringBuffer();
        m.addAttribute("studentList",list);
        m.addAttribute("gradeList",gradeService.getGradeList());
        if (gradeId != -500){
            m.addAttribute("gradeId",gradeId);
            m.addAttribute("provinceList",gradeClassesService.getGradeClassesList(gradeId));
            sb.append("&gradeId="+gradeId);
        }else{
            m.addAttribute("gradeId",0);
        }
        if(classes != -500){
            m.addAttribute("classes",classes);
            sb.append("&classes="+classes);
        }else{
            m.addAttribute("classes",0);
        }
        m.addAttribute("params",sb.toString());
        m.addAttribute("pageTitle","学生管理");
        setAdminMsg(m, request, session);
        return "studentListPage";
    }
    /**
     *新增学生
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertStudent")
    @ResponseBody
    public HashMap<String,Object> insertStudent(Student s, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(s!=null){
                Date d = new Date();
                s.setCreateTime(d);
                s.setUpdateTime(d);
//                exists.setOperaterId();=======================================================
                s.setStatus(1);
                int result = studentService.insertStudent(s);
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
     *根据id查找学生
     */
    @RequestMapping(value = "/getStudentById")
    @ResponseBody
    public HashMap<String,Object> getStudentById(Integer studentId,Integer gradeId, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        if (studentId != null){
            Student s = studentService.getStudent(studentId);
            List<Map> resultList = gradeClassesService.getGradeClassesList(gradeId);
            if (s != null){
                status = MessageConstant.SUCCESS_CODE;
                message = MessageConstant.SUCCESS_INFO;
                data.put("student",s);
                data.put("gradeId",gradeId);
                data.put("classesList",resultList);
            }
        }
        return CommonUtil.ToResultHashMap(status,message,data);
    }
    /**
     *编辑学生
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/updateStudent")
    @ResponseBody
    public HashMap<String,Object> updateStudent(Student s, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        try {
            Student studentForUpdate = studentService.getStudent(s.getId());
            if(studentForUpdate != null){
                Date d = new Date();
                studentForUpdate.setGradeClassesId(s.getGradeClassesId());
                studentForUpdate.setName(s.getName());
                studentForUpdate.setSex(s.getSex());
                studentForUpdate.setTelephone(s.getTelephone());
                studentForUpdate.setSeatNumber(s.getSeatNumber());
                studentForUpdate.setUpdateTime(d);
                int result = studentService.updateStudent(studentForUpdate);
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
     *删除学生
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteStudent")
    @ResponseBody
    public HashMap<String,Object> deleteStudent(int studentId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            studentService.deleteStudentHomeworkRecord(studentId);
            int result = studentService.deleteStudent(studentId);
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
