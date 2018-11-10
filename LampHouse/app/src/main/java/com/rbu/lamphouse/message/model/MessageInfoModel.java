package com.rbu.lamphouse.message.model;

import android.content.Context;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 9:33
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface MessageInfoModel {
    boolean checkMsg(Gson gson,String response);

    String getToken();

    String reSizeImgJaveScript();

}
