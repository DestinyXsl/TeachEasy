package com.homework.teach.controller;

import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.Admin;
import com.homework.teach.domain.Subject;
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
@RequestMapping("/statistical")
public class StatisticalController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    public IGradeClassesService gradeClassesService;
    @Autowired
    private IOperateLogService operateLogService;
    @Autowired
    private IHomeworkService homeworkService;

    /**
     *查询作业统计列表
     */
    @ApiOperation(value = "查询作业统计列表", notes = "查询作业统计列表接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "gradeNum", value = "年级", paramType = "body", required = false, dataType = "int"),
//            @ApiImplicitParam(name = "classes", value = "班", paramType = "body", required = false, dataType = "int")
//    })
    @RequestMapping(value = "/getHomeworkStatisticalList",method = RequestMethod.GET)
    public String getHomeworkStatisticalList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
//        int gradeNum = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeNum"),"-500"));
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);

        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        List<Map> resultList = homeworkService.getHomeworkStatisticalList(a.getId());
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        m.addAttribute("statisticalList",list);
        m.addAttribute("params","");
        m.addAttribute("pageTitle","数据统计");
        setAdminMsg(m, request, session);
        return "statisticalChartListPage";
    }

    /**
     *查看统计图
     */
    @ApiOperation(value = "查看统计图", notes = "查看统计图接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeClassesId", value = "班级Id", paramType = "body", required = false, dataType = "int"),
            @ApiImplicitParam(name = "subjectId", value = "科目Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getStatisticalChart",method = RequestMethod.GET)
    public String getStatisticalChart(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
        int gradeClassesId = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeClassesId"),"-500"));
        int subjectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("subjectId"),"-500"));

//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);

//        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
//        Admin a = (Admin)adminMsg.get("admin");
//        List<Map> resultList = homeworkService.getHomeworkStatisticalList(a.getId());
//        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
//        m.addAttribute("statisticalList",list);
        m.addAttribute("pageTitle","数据统计");
        m.addAttribute("gradeClassesId",gradeClassesId);
        m.addAttribute("subjectId",subjectId);
        setAdminMsg(m, request, session);
        return "statisticalChart";
    }
    @ApiOperation(value = "根据班级Id,科目Id获取统计数据", notes = "根根据班级Id,科目Id获取统计数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gradeClassesId", value = "班级Id", paramType = "body", required = false, dataType = "int"),
            @ApiImplicitParam(name = "subjectId", value = "科目Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getChart",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getChart(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int gradeClassesId = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeClassesId"),"-500"));
        int subjectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("subjectId"),"-500"));
        List<Map> resultList = homeworkService.getChart(gradeClassesId,subjectId);

        System.out.println("gggsss"+gradeClassesId+"::"+subjectId);
//        m.addAttribute("gradeClassesList",resultList);
//        if(resultList != null && resultList.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
//        }
        return CommonUtil.ToResultHashMap(status,message,resultList);
//        return "gradeClassListPage";
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
