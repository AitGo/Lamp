package com.rbu.lamphouse.setting.model;

import android.content.Context;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.utils.BleUtils;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 16:50
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProfilesModelImpl implements ProfilesModel{

    private Context mContext;
    private BluetoothClient mClient;

    public ProfilesModelImpl(Context context) {
        this.mContext = context;
        mClient = BleUtils.getInstanceClient(context);
    }

    @Override
    public void saveR(int r) {
        Constants.R = r;
    }

    @Override
    public void saveG(int g) {
        Constants.G = g;
    }

    @Override
    public void saveB(int b) {
        Constants.B = b;
    }

    @Override
    public void saveLight(int light) {
        Constants.Light = light;
    }

    @Override
    public void setLampRGB() {
        byte[] mBytes = new byte[]{BleUtils.i2b(Constants.B),BleUtils.i2b(Constants.G),BleUtils.i2b(Constants.R),BleUtils.i2b(Constants.Light)};
        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.colorUUID , mBytes, new BleWriteResponse() {
                @Override
                public void onResponse(int code) {
                    if (code == 0) {
                        BluetoothLog.e("setLampRGB success");
                    }else {
                        BluetoothLog.e("setLampRGB fail");
                    }
                }
            });
        }
    }
}
