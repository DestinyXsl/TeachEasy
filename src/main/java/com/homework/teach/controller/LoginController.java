package com.homework.teach.controller;

import com.homework.teach.domain.Admin;
import com.homework.teach.service.IAdminService;
import com.homework.teach.service.IMenuService;
import com.homework.teach.util.CommonUtil;
import com.homework.teach.util.RedisComponentUtil;
import com.homework.teach.util.constant.MessageConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api("登录相关接口controller")
public class LoginController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    public RedisComponentUtil redisComponentUtil;
//    @GetMapping("/")
//    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
//        model.addAttribute("name", account);
//        return "index";
//    }

    @GetMapping("/login")
    @ApiOperation(value = "管理后台登录界面", notes = "返回管理后台登录界面")
    public String login() {
        return "login";
    }
    @GetMapping("/wellcome")
    @ApiOperation(value = "管理后台首页界面", notes = "返回管理后台首页界面")
    public String wellcome(Model m, HttpServletRequest request, HttpSession session) {
        setAdminMsg(m, request, session);
//        return "wellcomeTrainingMode";
        return "wellcome";
    }
    @PostMapping("/loginPost")
    @ApiOperation(value = "管理后台登录接口", notes = "提交账号密码进行管理后台登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "管理员账号", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "管理员密码", paramType = "query", required = true, dataType = "String")
    })
    public @ResponseBody HashMap<String,Object> loginPost(String account, String password, HttpSession session, HttpServletResponse response) {
        int status = MessageConstant.ERROR_CODE;
        String message = MessageConstant.ERROR_INFO_DEMO;
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> adminMsg = new HashMap<>();
//        Map<String, Object> map = new HashMap<>();
        Admin admin = adminService.adminLogin(account,password);
        if (admin == null) {
//            map.put("success", false);
//            map.put("message", "密码错误");
            message = "密码错误";
            return CommonUtil.ToResultHashMap(status,message,data);
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        Cookie c = new Cookie("account",account);
        response.addCookie(c);
        c = new Cookie("name",admin.getName());
        response.addCookie(c);
//        session.getId()

        List<Map> resultList = menuService.getAdminParentMenu(admin.getRoleId());
        for (Map pMenu : resultList){
            //select m.id,m.title from menu m,menu_role mr where mr.menu_id = m.id and m.`status` = 1 and m.type = 2 and m.parent_menu_no = ? and mr.role_id = ? and mr.`status` = 1
            pMenu.put("children",menuService.getAdminSonMenu(CommonUtil.getStr(pMenu.get("menuNo"),""),admin.getRoleId()));
        }

        adminMsg.put("admin",admin);
        adminMsg.put("menu",resultList);
        redisComponentUtil.set(session.getId(),adminMsg);
//        map.put("success", true);
//        map.put("message", "登录成功");
        status = MessageConstant.SUCCESS_CODE;
        message = "登录成功";
        return CommonUtil.ToResultHashMap(status,message,data);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        redisComponentUtil.delete(session.getId());
        return "redirect:login";
    }

    @GetMapping("/changePwd")
    public String changePwd(HttpSession session) {
        return "changePwd";
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