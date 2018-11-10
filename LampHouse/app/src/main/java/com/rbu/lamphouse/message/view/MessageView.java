package com.rbu.lamphouse.message.view;

import android.support.v4.app.Fragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface MessageView {

    void switchFragment(Fragment fragment);

    void showFragment(Fragment fragment);

    void addFragment();

    void hideFragment();

    void chooseUnReadTab();

    void chooseReadTab();

    void setEditVisibility(int visibility);

    void setAllVisibility(int visibility);

    void setDoneVisibility(int visibility);

    void setDeleteVisibility(int visibility);

    Fragment getUnReadFragment();

    Fragment getReadFragment();

}
