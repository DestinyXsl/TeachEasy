package com.homework.teach.util.constant;

/**
 * methodName system
 * Created by yes_tao on 2017/11/27.17:43
 */
public class MessageConstant {
    public static final String ERROR_INFO = "失败，服务器有异常";
    public static final String ERROR_INFO_DEMO = "提交失败，请稍后重试";
    public static final String SUCCESS_INFO = "成功";

    public static final String NOT_FIND_DATA = "业务异常：没找到相关数据";
    public static final String PARAM_ERROR = "参数异常";

    public static final String NOT_LOGIN_USER = "用户尚未登录";
    public static final String NOT_AUTHENTICATION_USER = "用户尚未认证";

    public static final String NOT_PERMISSION = "你没有相关权限";
    public static final String NOT_ORGANIZE_TYPE = "组织类型不能为空";

    public static final String NOT_ORGAN_ID = "机构ID不能为空";
    public static final String NOT_USER_ID = "用户ID不能为空";
    public static final String NOT_SERVER_ID = "服务站ID不能为空";
    public static final String NOT_STATUS_TYPE = "状态类型不能为空";

    public static final String NOT_FIND_USERINFO = "没有找到相关用户信息";
    public static final String NOT_FIND_SERVERINFO = "没有找到相关服务站数据";
    public static final String NOT_FIND_ORGANIZEINFO = "没有找到相关机构信息";

    public static final String USERNAME_BE_USED = "用户名已被注册，请重试";
    public static final String USER_BE_REGISTER = "该从业人员已创建";
    public static final String MOBILE_BE_USED = "手机号码已被注册，请重试";
    public static final String NOT_PARAMS = "所传数据不能为空";
    public static final String NOT_FIND_USER_ORGAN = "没找到登陆用户所属机构";
    public static final String NOT_SAME_PASSWORD = "所输密码不一致，请重试";
    public static final String TITLE_BE_USED = "单位名称已被注册，请重试";

    public static final String INVALIDED_LEASING = "您好，您所属机构为经纪机构";

    public static final String NOT_REGIST_VIP = "没有找到该账号所属机构";
    public static final String ALREADY_APPLY_MEMBERSHIP = "该账号所属机构已为协会会员";
    public static final String ALREADY_APPLY_MEMBERSHIP_NOT_PAY = "该账号所属机构已提交会员申请，尚未缴费，请进行下一步操作";

    //下拉框参数
    public static final String OPTION_ID_TYPE = "id_types";//证件名称
    public static final String OPTION_MONEY_TYPE = "currencies";//货币类型
    public static final String OPTION_SEXS = "sexs";// 性别
    public static final String OPTION_DEGREES = "degrees";//学历
    public static final String OPTION_DISTRICTS = "districts";//区县

    //协会管理人员角色
    public static final String ROLE_ASSOCIATION_ADMIN = "association_admin";
    //经纪机构管理员角色
    public static final String ROLE_ORGANIZE_AGENT_ADMIN = "organize_agent_admin";
    //租赁机构管理员角色
    public static final String ROLE_ORGANIZE_LEASING_ADMIN = "organize_leasing_admin";
    //其他机构管理员角色
    public static final String ROLE_ORGANIZE_OTHER_ADMIN = "organize_other_admin";
    //网站用户
    public static final String ROLE_WEB_PERSON = "web_person";
    //会员
    public static final String ROLE_VIP = "vip";
    //用户管理员
    public static final String ROLE_USER_ADMIN = "association_user";

    //状态码（1成功，0失败）
    public static final int SUCCESS_CODE = 1;
    public static final int ERROR_CODE = 0;

    //通用状态（1有效，0无效）
    public static final long VAILD_STATUS = 1L;
    public static final long INVAILD_STATUS = 0L;

    //从业人员状态 (1从业，0未从业)
    public static final long BROKER_VAILD_STATUS = 1L;
    public static final long BROKER_INVAILD_STATUS = 0L;

    //审核状态 (0未审核,1通过,2不通过)
    public static final long AUDIT_WAIT_STATUS = 0L;
    public static final long AUDIT_PASS_STATUS = 1L;
    public static final long AUDIT_NOPASS_STATUS = 2L;

    //附件类型
    public static final long ATTACHMENT_VIP_LOGO_TYPE = 1L;//机构logo
    public static final long ATTACHMENT_PERSON_CARD_TYPE = 2L;//人员证件
    public static final long ATTACHMENT_RED_BLACK_TYPE = 3L;//红黑名单
    public static final long ATTACHMENT_EDU_FILE_TYPE = 4L;//培训课件

    //附件表文件类型
    public static final long ATTACHMENT_FILE_TYPE_PIC = 1L;//图片
    public static final long ATTACHMENT_FILE_TYPE_FILE = 2L;//文件

    //审核状态(0入职未审核,1入职通过,2入职不通过 3离职未审核 4离职通过 5离职不通过)
    public static final long BROKER_ENTRY_NOAUDIT = 0L;
    public static final long BROKER_ENTRY_PASS = 1L;
    public static final long BROKER_ENTRY_NOPASS = 2L;
    public static final long BROKER_LEAVE_NOAUDIT = 3L;
    public static final long BROKER_LEAVE_PASS = 4L;
    public static final long BROKER_LEAVE_NOPASS = 5L;

    //分页参数
    public static final String PAGE_INDEX = "1";
    public static final String PAGE_SIZE = "10";

    //excel文件后缀
    public static final String EXCEL_2003L =".xls";    //2003- 版本的excel
    public static final String EXCEL_2007U =".xlsx";   //2007+ 版本的excel

    //-3000到-3999：业务模块
    public static final int BUSINESS_ERROR_CODE = -3001;//业务异常
    public static final int PARAM_ERROR_CODE = -3002;//参数异常

}
