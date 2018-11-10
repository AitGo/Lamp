package com.rbu.lamphouse.device.model;

import com.rbu.lamphouse.diagnose.BluetoothEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface DeviceModel {

    boolean checkBLEFeature();

    void BluetoothDisConn(String mac);

    void array_unique(List<BluetoothEntity> list);

    void saveBluetoothMac(String mac);

    void saveAutoConn(boolean isAuto);

    void saveAutoDisconn(boolean isAuto);

    void setAutoConn(boolean isChecked);

    void setAutoDisconn(boolean isChecked);

    void saveTimerOff(boolean isOff);

    void saveTimerOn(boolean isOn);

    void setTimerOff(boolean isChecked,String data);

    void setTimerOn(boolean isChecked,String data);

    void saveOffTime(String OffTime);

    void saveOnTime(String OnTime);

    boolean getAutoConn();

    boolean getAutoDisconn();

    boolean getTimerOff();

    boolean getTimerOn();

    String getOffTime();

    String getOnTime();


}
