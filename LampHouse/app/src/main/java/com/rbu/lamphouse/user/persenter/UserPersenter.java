package com.rbu.lamphouse.user.persenter;

import android.app.Activity;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface UserPersenter {

    void startActivity(Activity activity);

    void login();

    void logout();

    void initData();

    void startCoupon();
}
