package com.rbu.lamphouse.user.model;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:40
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface LoginModel {

    void saveEmail(String email);

    void saveToken(String token);

    void savePassword(String password);

    boolean checkEmail(String email);

    boolean checkPassword(String password);

    boolean checkMsg(Gson gson,String response);
}
