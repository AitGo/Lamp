package com.rbu.lamphouse.user.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface LoginView {

    void setEmail(String text);

    void setPassword(String text);

    String getEmail();

    String getPassword();

    void finishActivity();

    void showToast(String msg);

    void showProgress(String msg);

    void dismissProgress(String msg);
}
