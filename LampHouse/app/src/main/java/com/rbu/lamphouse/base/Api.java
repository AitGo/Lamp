package com.rbu.lamphouse.base;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/14 16:09
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Api {

    public static String URL_rap = "http://svn.rbu.cn:8088/mockjs/2";
    public static String URL_test = "http://172.20.3.217:8888";
    public static String URL = "http://172.30.91.10:8088/lightBoxAPP_javaCode";

    public static String BASEURL = URL;

    public static String emailLogin = BASEURL + "/app/login/doLogin";
    public static String tokenLogin = BASEURL + "/app/login/doLogin";
    public static String regist = BASEURL + "/app/register/user";
    public static String logout = BASEURL + "/app/login/logout";
    public static String tkjn = BASEURL + "/app/register/sendCode";
    public static String forgetPass = BASEURL + "/app/login/forgetPassword";

    public static String couponTotal = BASEURL + "/coupon/totalMoney";
    public static String couponList = BASEURL + "/coupon/user";
    public static String couponAdd = BASEURL + "/coupon/addCoupon";

    public static String messageList = BASEURL + "/app/list";
    public static String messageDelete = BASEURL + "/app/deleteMessage";
    public static String messageInfo = BASEURL + "/app/infoView";

}
