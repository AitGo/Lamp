package com.rbu.lamphouse.home.view;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface HomeView {

    void setEt_R(int r);

    void setEt_G(int g);

    void setEt_B(int b);

    int getEt_R();

    int getEt_G();

    int getEt_B();

    void setTvLight(String text);

//    //edittext失去焦点
    void lostFocus();

}
