package com.rbu.lamphouse.home.model;

import android.content.Context;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleReadResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.utils.BleUtils;
import com.rbu.lamphouse.utils.ColorUtils;
import com.rbu.lamphouse.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeModelImpl implements HomeModel{

    private Context mContext;
    private BluetoothClient mClient;

    public HomeModelImpl(Context context) {
        this.mContext = context;
        mClient = BleUtils.getInstanceClient(context);
    }

    @Override
    public void setLampRGB() {
        byte[] mBytes = new byte[]{BleUtils.i2b(Constants.B)
                                    ,BleUtils.i2b(Constants.G)
                                    ,BleUtils.i2b(Constants.R)
                                    ,BleUtils.i2b(Constants.Light)};
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


    @Override
    public void setLampLight(int light) {
        Constants.Light = light;
    }

    @Override
    public int getRGBFromColor(int color, String type) {
        return ColorUtils.getRGBFromColor(color,type);
    }

    @Override
    public String getBluetoothMac() {
        return (String) SPUtils.getParam(mContext,Constants.BlueToothMac,"");
    }

    @Override
    public int isBluetoothConn(String mac) {
        return mClient.getConnectStatus(mac);
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
    public void lampOff() {
        byte[] mBytes = new byte[]{BleUtils.i2b(0)
                                    ,BleUtils.i2b(0)
                                    ,BleUtils.i2b(0)
                                    ,BleUtils.i2b(0)};
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

    @Override
    public void lampOn() {
        byte[] mBytes = new byte[]{BleUtils.i2b(0)
                                    ,BleUtils.i2b(0)
                                    ,BleUtils.i2b(0)
                                    ,BleUtils.i2b(255)};
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

    @Override
    public void checkLamp() {

        char a = new Character('A');
        char t = new Character('T');
        char add = new Character('+');
        char c = new Character('C');
        char o = new Character('O');
        char l = new Character('L');
        char r = new Character('R');

        byte[] mBytesTime = new byte[]{(byte)a,(byte)t,(byte)add,(byte)c,(byte)o,(byte)l,(byte)o,(byte)r};

        final String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.colorUUID, mBytesTime, new BleWriteResponse() {
                @Override
                public void onResponse(int code) {
                    if (code == 0) {
                        BluetoothLog.e("ble success");
                        mClient.read(mac, Constants.serviceUUID, Constants.colorUUID, new BleReadResponse() {
                            @Override
                            public void onResponse(int code, byte[] data) {
                                boolean isOFF = true;
                                for(int i = 0; i < data.length; i++) {
                                    if((data[i] & 0xff) != 0x00) {
                                        isOFF = false;
                                        break;
                                    }
                                }
                                if(isOFF) {
                                    EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_LAMP_OFF));
                                }else {
                                    EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_LAMP_ON));
                                }

                            }
                        });
                    }
                }
            });
        }
    }
}
