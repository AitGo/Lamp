package com.rbu.lamphouse.message.persenter;

import android.support.v4.app.Fragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface MessagePersenter {

    void showUnReadFragment(Fragment fragment);

    void showReadFragment(Fragment fragment);

    void edit();

    void all();

    void done();

    void delete();
}
