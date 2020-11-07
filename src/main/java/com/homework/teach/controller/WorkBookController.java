package com.homework.teach.controller;
import java.util.Date;

import com.github.pagehelper.PageHelper;
import com.homework.teach.domain.*;
import com.homework.teach.service.*;
import com.homework.teach.util.BarCodeUtils;
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
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/workBook")
public class WorkBookController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
    @Autowired
    public IGradeClassesService gradeClassesService;
    @Autowired
    private IOperateLogService operateLogService;
    @Autowired
    private IWorkBookService workBookService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IWorkBookTcodeService workBookTcodeService;
    /**
     *作业本列表查询
     *管理后台查询作业本列表接口
     */
    @ApiOperation(value = "作业本列表查询", notes = "管理后台查询作业本列表接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "gradeNum", value = "年级", paramType = "body", required = false, dataType = "int"),
//            @ApiImplicitParam(name = "classes", value = "班", paramType = "body", required = false, dataType = "int")
//    })
    @RequestMapping(value = "/getWorkBookList",method = RequestMethod.GET)
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
        List<Map> resultList = workBookService.getWorkBookList(a.getId());
        PageBean<Map> list = new PageBean<Map>(resultList);
//        return CommonUtil.ToResultHashMap(status,message,list);
        m.addAttribute("workBookList",list);
        m.addAttribute("gradeList",gradeService.getGradeList());
        m.addAttribute("subjectList",subjectService.getSubjectList());
        m.addAttribute("params","");
        m.addAttribute("pageTitle","作业管理");
        setAdminMsg(m, request, session);
        return "workBookListPage";
    }

    @ApiOperation(value = "根据科目Id获取作业本列表", notes = "根据科目Id获取作业本列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "科目Id", paramType = "body", required = false, dataType = "int"),
            @ApiImplicitParam(name = "classesId", value = "班级Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getWorkBookListBySubjectId",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,Object> getWorkBookListBySubjectId(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int subjectId = Integer.parseInt(CommonUtil.getStr(request.getParameter("subjectId"),"-500"));
        int classesId = Integer.parseInt(CommonUtil.getStr(request.getParameter("classesId"),"-500"));

        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        List<Map> resultList = workBookService.getWorkBookListByAdminId(a.getId(),subjectId,classesId);

//        m.addAttribute("gradeClassesList",resultList);
        if(resultList != null && resultList.size() > 0){
            status = MessageConstant.SUCCESS_CODE;
            message = MessageConstant.SUCCESS_INFO;
        }
        return CommonUtil.ToResultHashMap(status,message,resultList);
//        return "gradeClassListPage";
    }
    @ApiOperation(value = "根据作业本id获取条形码", notes = "根据作业本id获取条形码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workBookId", value = "作业本Id", paramType = "body", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getTcodeByWorkBookId",method = RequestMethod.GET)
    public String getTcodeByWorkBookId(Model m,HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        int workBookId = Integer.parseInt(CommonUtil.getStr(request.getParameter("workBookId"),"-500"));

        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");
        List<String> resultList = workBookService.getTcodeByWorkBookId(a.getId(),workBookId);

        for(String ss : resultList){
            System.out.println(ss);
        }
//        m.addAttribute("gradeClassesList",resultList);
        m.addAttribute("tcodeList",resultList);
        m.addAttribute("pageTitle","作业管理");
        setAdminMsg(m, request, session);
        return "tcodePage";
    }
    /**
     *新增作业本
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/insertWorkBook")
    @ResponseBody
    public HashMap<String,Object> insertWorkBook(WorkBook wb, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();

        HashMap<String,Object> adminMsg = (HashMap<String,Object>)redisComponentUtil.get(session.getId());
        Admin a = (Admin)adminMsg.get("admin");

        try {
            if(wb!=null){
                wb.setAdminId(a.getId());
                int existWorkBook = workBookService.getWorkBookByName(wb);
                if (existWorkBook != 0){
                    return CommonUtil.ToResultHashMap(status,"该作业本已存在!",data);
                }
                Date d = new Date();
                wb.setCreateTime(d);
                wb.setUpdateTime(d);
                wb.setStatus(1);
                int result = workBookService.insertWorkBook(wb);
                if (result == 1){
                    String tcode = tcodeMethod(wb);
                    wb.setBaseTcode(tcode);
                    result = workBookService.updateWorkBookTcode(wb);

                    //生成条形码图片
//                    String basePath = System.getProperty("user.dir");
//                    String classpath = ResourceUtils.getURL("classpath:").getPath().substring(1);
//                    System.out.println("basePath:"+basePath);
//                    System.out.println("classpath:"+classpath);
//                    String path = basePath + "\\src\\main\\resources\\static\\tcodeImgs\\";
//                    String imgPath = classpath + "static/tcodeImgs/";
//                    System.out.println("realPath::::::"+session.getServletContext().getRealPath(""));
//                    path += tcode;
//                    imgPath += tcode;
//                    BufferedImage image;
//                    BufferedImage image = BarCodeUtils.insertWords(BarCodeUtils.getBarCode("200001010100038"), "(1年2班)(英语)(听写本)(小明)");
//                    ImageIO.write(image, "jpg", new File(path+"200001010100038"+".jpg"));

//                    List<Integer> students = homeworkService.getStudentIdByHomework(homeworkId);
                    List<Student> students = workBookService.getStudentIdByWorkBook(wb.getGradeClassesId());
                    String tcodeCN = workBookService.getTcodeCN(wb.getId());
                    tcodeCN += "("+wb.getName()+")";
                    DecimalFormat g1=new DecimalFormat("00000");
                    String code = "",description = "";
                    for(Student s : students) {
                        code = tcode + g1.format(s.getSeatNumber());
                        description = tcodeCN+"("+s.getName()+")";
//                        image = BarCodeUtils.insertWords(BarCodeUtils.getBarCode(code), description);

//                        ImageIO.write(image, "jpg", new File(path + g1.format(s.getId())+".jpg"));
//                        System.out.println(path + g1.format(s.getId())+".jpg");

//                        ImageIO.write(image, "jpg", new File(imgPath + g1.format(s.getId())+".jpg"));
//                        System.out.println(imgPath + g1.format(s.getId())+".jpg");

                        WorkBookTcode wbt = new WorkBookTcode();
                        wbt.setWorkBookId(wb.getId());
                        wbt.setStudentId(s.getId());
                        wbt.setTcode(code);
                        wbt.setDescription(description);
                        wbt.setCreateTime(new Date());
                        wbt.setIsDelete(0);
                        workBookTcodeService.insertWorkBookTcode(wbt);
                    }
                    if(result == 1){
                        status = MessageConstant.SUCCESS_CODE;
                        message = MessageConstant.SUCCESS_INFO;
                    }
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
    public String tcodeMethod(WorkBook wb){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        SimpleDateFormat formatter = new SimpleDateFormat("yy");
        String year = formatter.format(new Date())+(month >=8?1:0);
        DecimalFormat g1=new DecimalFormat("000");
        DecimalFormat g2=new DecimalFormat("00");
        String gradeClasses = g1.format(wb.getGradeClassesId());
        String subject = g2.format(wb.getSubjectId());
        String workBook = g2.format(wb.getId());
        String result = year+gradeClasses+subject+workBook;
        System.out.println(result);
        return result;
    }
    /**
     *删除作业本
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/deleteWorkBook")
    @ResponseBody
    public HashMap<String,Object> deleteWorkBook(int workBookId, HttpServletRequest request,HttpSession session) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
//        Admin a = getAdminMsg(session);
        try {
            //作业
            int exist = workBookService.existHomework(workBookId);
            if(exist > 0){
                return CommonUtil.ToResultHashMap(status,"该作业本存在作业数据，不能删除。",data);
            }

            int result = workBookService.deleteWorkBook(workBookId);
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
