package com.rbu.lamphouse.base;

import android.support.v4.app.Fragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 9:33
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface BaseFragmentView {

    void showToast(String msg);

    void switchFragment(Fragment fragment);
}
