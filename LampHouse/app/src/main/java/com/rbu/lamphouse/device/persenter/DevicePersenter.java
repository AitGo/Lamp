package com.rbu.lamphouse.device.persenter;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.search.SearchResult;
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
public interface DevicePersenter {
    void search(BluetoothClient mClient);

    void deviceFound(SearchResult device,List<BluetoothEntity> unConnList);

    void deviceConn(String mac,int position);

    void deviceConnResponse(int code,int position);

    void stopConn(String mac,int position);

}
