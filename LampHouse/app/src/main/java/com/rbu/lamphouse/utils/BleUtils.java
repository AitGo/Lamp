package com.rbu.lamphouse.utils;

import android.content.Context;

import com.inuker.bluetooth.library.BluetoothClient;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/7 12:11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class BleUtils {

    private static BluetoothClient mClient;

    public BleUtils(){
    }

    public static BluetoothClient getInstanceClient(Context context) {
        if (mClient == null) {
            mClient = new BluetoothClient(context);
        }
        return mClient;
    }

    public static byte i2b(int i) {
        Integer integer = new Integer(i);
        return integer.byteValue();
    }


}
