package com.rbu.lamphouse.user.model.coupon;

import com.google.gson.Gson;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 17:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface UnusedModel {

    boolean checkMsg(Gson gson, String response);

    String getToken();
}
