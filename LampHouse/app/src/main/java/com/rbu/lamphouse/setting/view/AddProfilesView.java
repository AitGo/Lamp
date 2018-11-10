package com.rbu.lamphouse.setting.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 15:11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface AddProfilesView {

    void finishActivity();

    void setEt_R(int r);

    void setEt_G(int g);

    void setEt_B(int b);

    String getEt_R();

    String getEt_G();

    String getEt_B();

    void setTvLight(String text);

    int getLight();

    void showEditDialog();

    void showToast(String msg);
}
