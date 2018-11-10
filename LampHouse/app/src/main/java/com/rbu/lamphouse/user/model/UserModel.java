package com.rbu.lamphouse.user.model;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:56
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface UserModel {
    String getEmail();

    String getToken();

    void setToken(String token);

    void setEmail(String email);

    boolean checkMsg(Gson gson,String response);
}
