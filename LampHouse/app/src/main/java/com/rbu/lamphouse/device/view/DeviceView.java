package com.rbu.lamphouse.device.view;

import com.rbu.lamphouse.diagnose.BluetoothEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:51
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface DeviceView {


    void BluetoothScan();

    void BluetoothConn(String mac,int position);

    void setConnListVisibility(int visibility);

    void setUnConnListvisibility(int visibility);

    List<BluetoothEntity> getConnList();

    List<BluetoothEntity> getUnConnList();

    void notifyConnList();

    void notifyUnConnList();

    void showProgress(String msg);

    void showDialog(String msg);

    void dismissProgress();
}
