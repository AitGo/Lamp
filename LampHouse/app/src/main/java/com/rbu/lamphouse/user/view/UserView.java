package com.rbu.lamphouse.user.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface UserView {

    void setUserName(String name);

    String getUserName();

    void SetLoginVisibility(int visibility);

    void SetRegistVisibility(int visibility);

    void setLogouttVisibility(int visibility);

    void showProgress(String msg);

    void dismissProgress(String msg);

    void showToast(String msg);
}
