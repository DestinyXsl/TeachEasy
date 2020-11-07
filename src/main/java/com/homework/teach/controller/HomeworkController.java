package com.homework.teach.controller;

import com.github.pagehelper.PageHelper;
import com.homework.teach.config.WebSocketServer;
import com.homework.teach.domain.*;
import com.homework.teach.domain.vo.HomeworkRecordVo;
import com.homework.teach.domain.vo.UpdateHomeworkRecordVo;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/homework")
public class HomeworkController {

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
    @Autowired
    private IHomeworkRecordService homeworkRecordService;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IWorkBookService workBookService;
    /**
     *作业列表查询
     *管理后台查询作业列表接口
     */
    @ApiOperation(value = "作业列表查询", notes = "管理后台查询作业列表接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "gradeNum", value = "年级", paramType = "body", required = false, dataType = "int"),
//            @ApiImplicitParam(name = "classes", value = "班", paramType = "body", required = false, dataType = "int")
//    })
    @RequestMapping(value = "/getHomeworkList",method = RequestMethod.GET)
    public String getGradeClassList(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
//        int gradeNum = Integer.parseInt(CommonUtil.getStr(request.getParameter("gradeNum"),"-500"));
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        List<Map> resultList = homeworkService.getHomeworkList(a.getId());
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);

        m.addAttribute("menu",adminMsg.get("menu"));
        m.addAttribute("homeworkList",list);
        m.addAttribute("gradeList",gradeService.getGradeList());
        m.addAttribute("subjectList",subjectService.getSubjectList());
        m.addAttribute("params","");
//        m.addAttribute("workBookList",workBookService.getWorkBookListByAdminId(a.getId()));
        m.addAttribute("pageTitle","作业管理");

        return "homeworkListPage";
    }

    /**
     *登记作业
     *管理后台登记作业接口
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "登记作业", notes = "管理后台登记作业接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "homeworkId", value = "作业Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/registerHomework",method = RequestMethod.GET)
    public String registerHomework(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int homeworkId = Integer.parseInt(CommonUtil.getStr(request.getParameter("homeworkId"),"-500"));
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));
        try{
    //        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
            Homework homework = homeworkService.getHomework(homeworkId);

            if(homework.getHomeworkStatus() == 1){
                String tcode = workBookService.getBaseTcodeById(homework.getWorkBookId());
//                System.out.println(tcode);
                List<Student> students = homeworkService.getStudentIdByHomework(homeworkId);
                homeworkRecordService.insertHomeworkRecord(homeworkId,students,tcode);
                homework.setHomeworkStatus(2);
                homeworkService.updateHomework(homework);
            }
//            PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
            List<Map> result = homeworkRecordService.getStudentHomeworkRecordList(homeworkId);
//            PageBean<Map> list = new PageBean<Map>(result);
    //        return CommonUtil.ToResultHashMap(status,message,list);
            m.addAttribute("homeworkRecordList",result);
            redisComponentUtil.set(session.getAttribute("user").toString(),homeworkId);
            m.addAttribute("pageTitle","作业管理");
            setAdminMsg(m, request, session);
            return "homeworkRegisterPage";
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }
//    public String tcodeMethod(Homework homework){
//        Calendar cal = Calendar.getInstance();
//        int month = cal.get(Calendar.MONTH) + 1;
//        SimpleDateFormat formatter = new SimpleDateFormat("yy");
//        String year = formatter.format(new Date())+(month >=8?1:0);
//        DecimalFormat g1=new DecimalFormat("000");
//        DecimalFormat g2=new DecimalFormat("00");
//        String gradeClasses = g1.format(homework.getGradeClassesId());
//        String subject = g2.format(homework.getSubjectId());
//        String workBook = g2.format(homework.getWorkBookId());
//        String result = year+gradeClasses+subject+workBook;
//        System.out.println(result);
//        return result;
//    }

//    @ApiOperation(value = "手动调用交作业", notes = "手动调用交作业")
//    @PostMapping(value = "/beRegisterHomework")
//    @ResponseBody
//    public void beRegisterHomework(@Valid @RequestBody String body) {
//        int status = MessageConstant.ERROR_CODE;
//        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
////        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
////        String tcode = CommonUtil.getStr(request.getParameter("tcode"),"");
////        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));
//
//        System.out.println("vvvvvvvvvv::::::"+body);
////        try {
////            webSocketServer.sendInfo(tcode,"xiaoming");
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (EncodeException e) {
////            e.printStackTrace();
////        }
////        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
////        List<Map> resultList = homeworkService.getHomeworkList();
////        PageBean<Map> list = new PageBean<Map>(resultList);
////        return CommonUtil.ToResultHashMap(status,message,list);
////        m.addAttribute("homeworkList",list);
////        m.addAttribute("pageTitle","作业管理");
////        setAdminMsg(m, request, session);
////        return "homeworkListPage";
//    }
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "手动调用交作业", notes = "手动调用交作业")
    @RequestMapping(value = "/beRegisterHomework",method = RequestMethod.POST)
    @ResponseBody
    public void beRegisterHomework(@Valid @RequestBody String body) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
//        HashMap<String,Object> data = new HashMap<>();
//        PageHelper.startPage(Integer.parseInt(CommonUtil.getStr(request.getParameter("pageNum"), "1")), Integer.parseInt(CommonUtil.getStr(request.getParameter("pageSize"), "10")));//第几页,,,每页多少条记录
//        String tcode = CommonUtil.getStr(request.getParameter("tcode"),"");
//        int classes = Integer.parseInt(CommonUtil.getStr(request.getParameter("classes"),"-500"));

//        System.out.println("ttttttttttttt::::::"+body);
        try {
            HomeworkRecordVo hr = homeworkRecordService.getHomeworkRecordByTCode(body);
            if(hr == null){
                return ;
            }
//            System.out.println(hr.toString());
//            String homeworkId = redisComponentUtil.get(hr.getAccountName()).toString();
            System.out.println(body+"::::homeworkId::::"+redisComponentUtil.get(hr.getAccountName()).toString());
            hr = homeworkRecordService.getHomeworkRecordByHWIdAndTCode(redisComponentUtil.get(hr.getAccountName()).toString(),body);
            if (hr == null){
                return;
            }
            hr.setRecordStatus(1);
            homeworkRecordService.updateHomeworkRecord(hr);
            webSocketServer.sendInfo(body,hr.getAccountName());
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
//        List<GradeClasses> resultList = gradeClassesService.getGradeClassesList(gradeNum,classes);
//        List<Map> resultList = homeworkService.getHomeworkList();
//        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
//        m.addAttribute("homeworkList",list);
//        m.addAttribute("pageTitle","作业管理");
//        setAdminMsg(m, request, session);
//        return "homeworkListPage";
    }

    /**
     *手动修改作业状态
     */
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "手动修改作业状态")
    @RequestMapping(value = "/setHomeworkStatus")
    @ResponseBody
    public HashMap<String,Object> setHomeworkStatus(UpdateHomeworkRecordVo body) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        try {
            if(body!=null){
                System.out.println("toString::"+body.toString());
                int result = 0;
                if(body.getType() == 1){
                    result = homeworkRecordService.updateHomeworkRecordStatus(body);
                }else if(body.getType() == 2){
                    result = homeworkRecordService.updateHomeworkRecordLevel(body);
                }
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
     *新增作业
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertHomework")
    @ResponseBody
    public HashMap<String,Object> insertHomework(Homework h, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        try {
            if(h!=null){
                Date d = new Date();
                h.setAdminId(a.getId());
                h.setHomeworkStatus(1);
                h.setCreateTime(d);
                h.setUpdateTime(d);
//                exists.setOperaterId();=======================================================
                h.setStatus(1);
                int result = homeworkService.insertHomework(h);
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
     *删除作业
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteHomework")
    @ResponseBody
    public HashMap<String,Object> deleteHomework(int homeworkId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            homeworkService.deleteHomeworkRecord(homeworkId);
            int result = homeworkService.deleteHomework(homeworkId);
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
