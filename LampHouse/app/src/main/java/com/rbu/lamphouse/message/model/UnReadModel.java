package com.rbu.lamphouse.message.model;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface UnReadModel {
    String getToken();

    boolean checkMsg(Gson gson, String response);

    boolean checkStateMsg(Gson gson,String response);
}
