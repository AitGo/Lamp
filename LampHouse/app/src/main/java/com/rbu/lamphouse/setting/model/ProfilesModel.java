package com.rbu.lamphouse.setting.model;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 16:50
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface ProfilesModel {

    void saveR(int r);

    void saveG(int g);

    void saveB(int b);

    void saveLight(int light);

    void setLampRGB();
}
