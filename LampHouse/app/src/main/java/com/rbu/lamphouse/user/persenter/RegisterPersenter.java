package com.rbu.lamphouse.user.persenter;

import android.app.Activity;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:31
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface RegisterPersenter {

    void startActivity(Activity activity);

    void finishActivity();

    void getTkjn();

    void regist();
}
