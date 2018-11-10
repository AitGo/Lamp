package com.rbu.lamphouse.main.view;

import android.support.v4.app.Fragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 15:23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface MainView {

    void setHomeTab(boolean isCheck);

    void setDeviceTab(boolean isCheck);

    void setMessageTab(boolean isCheck);

    void setUserTab(boolean isCheck);

    void setTabUnCheck();

    void switchFragment(Fragment fragment);

    void showFragment(Fragment fragment);

    void addFragment();

    void hideFragment();

    void removeAllActivity();

    void showToast(String msg);

    Fragment getHomeFragment();

    Fragment getDeviceFragment();

    Fragment getMessageFragment();

    Fragment getUserFragment();

}
