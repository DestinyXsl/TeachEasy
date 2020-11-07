package com.homework.teach.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YES on 2017/7/6.
 */
public class CommonUtil {
    public static Map requestToMap(HttpServletRequest request, String[] paramNames) {
        Map paramMap = new HashMap();
        if (paramNames != null && paramNames.length > 0) {
            for (int i = 0; i < paramNames.length; i++) {
                String paramName = paramNames[i];
                String[] paramValue = request.getParameterValues(paramName);
                if (paramValue != null && paramValue.length > 1)
                    paramMap.put(paramName, paramValue);
                else if (paramValue != null && paramValue.length == 1){
                    String value = paramValue[0];
                    if(isEmptyStr(paramValue[0])) value="";
                    paramMap.put(paramName, value.trim());
                }
                else
                    paramMap.put(paramName, null);
            }
        } else {
            //System.out.println("传入的参数数组为null或长度为0");
        }
        return paramMap;
    }

    public static boolean isEmptyStr(String str){
        String[] options = new String[]{"null"};
        if(str==null) return true;
        String target = str.trim();
        if(target.equals("")) return true;
        for(int i=0;i<options.length;i++)
            if(target.equals(options[i])) return true;
        return false;
    }

    public static String getStr(Object str, String edefaultStrfaultStr){
        if(str==null || str.toString().trim().equals("")){
            return edefaultStrfaultStr;
        }
        return str.toString().trim();
    }

    public static boolean validateEnum(String [] enumList,String param,boolean isMult){
        boolean isEnum = false;

        if(param.equals("")){
            return isEnum;
        }

        if(isMult){
            String [] params = param.split(",");
            for(String p : params){
                boolean isSame = false;
                for(String enumParam : enumList){
                    if(p.equals(enumParam)){
                        isSame = true;
                        break;
                    }
                }

                if(!isSame){
                    isEnum = false;
                    break;
                }else{
                    isEnum = true;
                }
            }
        }else{
            for(String enumParam : enumList){
                if(enumParam.equals(param)){
                    isEnum = true;
                }
            }
        }

        return isEnum;
    }

    public static String nvl(String str,String defult){
        if(str == null || str.equals("")){
            return defult;
        }
        return str;
    }

    public static String replaceMsg(String msg){
        if(msg.indexOf("[people_1_telephone]") != -1){
            msg = "请提供有效的手机号码!";
        }
        if(msg.indexOf("[cert_owner_id_no]") != -1){
            msg = "请提供有效的持证人证件号码!";
        }
        if(msg.indexOf("[people_1_id_type]") != -1){
            msg = "请提供有效的证件名称!";
        }
        return msg;
    }

    public static HashMap<String,Object> ToResultHashMap(int status, String message, Object obj)  {
        HashMap<String,Object> map= new HashMap<>();
        map.put("status",status);
        map.put("message",message);
        if(obj != null){
            map.put("data",obj);
        }

        return map;
    }
}
