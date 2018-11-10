package com.rbu.lamphouse.user.model;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface RegisterModel {

    boolean checkMsg(Gson gson,String response);

    boolean checkStateMsg(Gson gson,String response);

    void saveToken(String token);

    void saveEmail(String email);
}
