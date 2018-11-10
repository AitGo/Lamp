package com.rbu.lamphouse.home.model;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface HomeModel {

    void setLampRGB();

    void setLampLight(int light);

    int getRGBFromColor(int color,String type);

    String getBluetoothMac();

    int isBluetoothConn(String mac);

    void saveR(int r);

    void saveG(int g);

    void saveB(int b);

    void lampOff();

    void lampOn();

    void checkLamp();
}
