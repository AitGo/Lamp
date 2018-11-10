package com.rbu.lamphouse.user.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:30
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface RegisterView {

    void finishActivity();

    void setEmail(String email);

    void setPassword(String password);

    void setTkjn(String tkjn);

    String getEmail();

    String getPassword();

    String getTkjn();

    void showToast(String msg);

    void startTimer();

    void cancelTimer();

    void showProgress(String msg);

    void dismissProgress(String msg);
}
