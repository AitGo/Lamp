package com.rbu.lamphouse.user.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/13 17:33
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface ForgetPasswordView {

    void finishActivity();

    String getEmail();

    void setEmail(String text);

    String getPassword();

    void setPassword(String text);

    String getPasswordConfirm();

    void setPasswordConfirm(String text);

    String getTkjn();

    void setTkjn(String text);

    void showToast(String msg);

    void startTimer();

    void cancelTimer();

    void showProgress(String msg);

    void dismissProgress(String msg);
}
