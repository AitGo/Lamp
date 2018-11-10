package com.rbu.lamphouse.main.persenter;

import android.support.v4.app.Fragment;

import com.rbu.lamphouse.base.BaseFragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 15:24
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface MainPersenter {

    void showHomeFragment(Fragment fragment);

    void showDeviceFragment(Fragment fragment);

    void showMessageFragment(Fragment fragment);

    void showUserFragment(Fragment fragment);

    void exit();

    void initConfigFile(String filePaht,String fileName);

    void initColorConfigFile(String filePaht,String fileName);

    void checkPath();

    void switchLanguage();
}
