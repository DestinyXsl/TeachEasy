package com.homework.teach.controller;

import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.GradeClasses;
import com.homework.teach.domain.Subject;
import com.homework.teach.service.*;
import com.homework.teach.util.CommonUtil;
import com.homework.teach.util.PageBean;
import com.homework.teach.util.RedisComponentUtil;
import com.homework.teach.util.constant.MessageConstant;
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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    public IGradeClassesService gradeClassesService;
    @Autowired
    private IOperateLogService operateLogService;
    @Autowired
    private ISubjectService subjectService;

    /**
     *学科列表查询
     *管理后台查询学科列表接口
     */
    @ApiOperation(value = "学科列表查询", notes = "管理后台查询学科列表接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "gradeNum", value = "年级", paramType = "body", required = false, dataType = "int"),
//            @ApiImplicitParam(name = "classes", value = "班", paramType = "body", required = false, dataType = "int")
//    })
    @RequestMapping(value = "/getSubjectList",method = RequestMethod.GET)
    public String getGradeClassList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
//        int gradeNum = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeNum"),"-500"));
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
        List<Subject> resultList = subjectService.getSubjectList();
        PageBean<Subject> list = new PageBean<Subject>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        m.addAttribute("subjectList",list);
        m.addAttribute("params","");
        m.addAttribute("pageTitle","学科管理");
        setAdminMsg(m, request, session);
        return "subjectListPage";
    }
    /**
     *新增学科
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertSubject")
    @ResponseBody
    public HashMap<String,Object> insertSubject(Subject s, HttpServletRequest request) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(s!=null){

                int existSubject = subjectService.existSubject(s);
                if (existSubject != 0){
                    return CommonUtil.ToResultHashMap(status,"该学科已存在!",data);
                }

                Date d = new Date();
                s.setCreateTime(d);
//                exists.setOperaterId();=======================================================
                s.setStatus(1);
                int result = subjectService.insertSubject(s);
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
     *删除学科
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteSubject")
    @ResponseBody
    public HashMap<String,Object> deleteSubject(int subjectId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            //作业
            int exist = subjectService.existHomework(subjectId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该学科存在作业数据，不能删除。",data);
            }
            //作业本
            exist = subjectService.existWorkBook(subjectId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该学科存在作业本数据，不能删除。",data);
            }

            int result = subjectService.deleteSubject(subjectId);
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
