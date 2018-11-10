package com.rbu.lamphouse.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 14:37
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class StringUtils {

    public static int string2int (String str) {
        return string2int(str,0);
    }
    public static int string2int (String str,int def) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 判断邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPassword(String password) {
        if(password.length() < 6) {
            return false;
        }else {
            return true;
        }

    }
}
