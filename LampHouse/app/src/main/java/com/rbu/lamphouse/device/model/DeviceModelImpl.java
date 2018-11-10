package com.rbu.lamphouse.device.model;

import android.content.Context;
import android.content.pm.PackageManager;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.BluetoothEntity;
import com.rbu.lamphouse.utils.BleUtils;
import com.rbu.lamphouse.utils.DataUtils;
import com.rbu.lamphouse.utils.SPUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class DeviceModelImpl implements DeviceModel{

    private Context mContext;
    private BluetoothClient mClient;

    public DeviceModelImpl(Context context) {
        this.mContext = context;
        mClient = BleUtils.getInstanceClient(context);
    }

    @Override
    public boolean checkBLEFeature() {
        return mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }


    @Override
    public void BluetoothDisConn(String mac) {
        mClient.disconnect(mac);
    }

    @Override
    public void array_unique(List<BluetoothEntity> list) {
        List<BluetoothEntity> temp = new LinkedList<>();
        for(BluetoothEntity a:list) {
            if(!temp.contains(a)) {
                temp.add(a);
            }else {
                list.remove(a);
            }
        }
    }

    @Override
    public void saveBluetoothMac(String mac) {
        SPUtils.setParam(mContext, Constants.BlueToothMac,mac);
    }

    @Override
    public void saveAutoConn(boolean isAuto) {
        SPUtils.setParam(mContext, Constants.AutoConnected,isAuto);
    }

    @Override
    public void saveAutoDisconn(boolean isAuto) {
        SPUtils.setParam(mContext, Constants.AutoDisconnected,isAuto);
    }

    @Override
    public void setAutoConn(boolean isChecked) {
        String hex = Integer.toHexString(DataUtils.getYear());
        String hex1 = null;
        String hex2 = null;
        if(hex.length() ==3) {
            hex1 = hex.substring(0,1);
            hex2 = hex.substring(1,hex.length());
        }else {
            hex1 = hex.substring(0,2);
            hex2 = hex.substring(2,hex.length());
        }
        //        boolean isAutoConn = (boolean) SPUtils.getParam(mContext,Constants.AutoConnected,false);
        boolean isAutoDisconn = (boolean) SPUtils.getParam(mContext,Constants.AutoDisconnected,false);

        byte[] mBytes = new byte[]{BleUtils.i2b(Integer.parseInt(hex1,16))
                ,BleUtils.i2b(Integer.parseInt(hex2,16))
                ,BleUtils.i2b(DataUtils.getMonth())
                ,BleUtils.i2b(DataUtils.getDay())
                ,BleUtils.i2b(DataUtils.getHour())
                ,BleUtils.i2b(DataUtils.getMinute())
                ,BleUtils.i2b(DataUtils.getSecond())
                ,BleUtils.i2b(DataUtils.getWay())
                ,BleUtils.i2b(isChecked ? 1 : 0)
                ,BleUtils.i2b(isAutoDisconn ? 1 : 0)};

        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.autoUUID , mBytes, new BleWriteResponse() {
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

    private void syTime() {
        String hex = Integer.toHexString(DataUtils.getYear());
        String hex1 = null;
        String hex2 = null;
        if(hex.length() ==3) {
            hex1 = hex.substring(0,1);
            hex2 = hex.substring(1,hex.length());
        }else {
            hex1 = hex.substring(0,2);
            hex2 = hex.substring(2,hex.length());
        }
        boolean isAutoConn = (boolean) SPUtils.getParam(mContext,Constants.AutoConnected,false);
        boolean isAutoDisconn = (boolean) SPUtils.getParam(mContext,Constants.AutoDisconnected,false);

        byte[] mBytes = new byte[]{BleUtils.i2b(Integer.parseInt(hex1,16))
                ,BleUtils.i2b(Integer.parseInt(hex2,16))
                ,BleUtils.i2b(DataUtils.getMonth())
                ,BleUtils.i2b(DataUtils.getDay())
                ,BleUtils.i2b(DataUtils.getHour())
                ,BleUtils.i2b(DataUtils.getMinute())
                ,BleUtils.i2b(DataUtils.getSecond())
                ,BleUtils.i2b(DataUtils.getWay())
                ,BleUtils.i2b(isAutoConn ? 1 : 0)
                ,BleUtils.i2b(isAutoDisconn ? 1 : 0)};

        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.autoUUID , mBytes, new BleWriteResponse() {
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
    public void setAutoDisconn(boolean isChecked) {
        String hex = Integer.toHexString(DataUtils.getYear());
        String hex1 = null;
        String hex2 = null;
        if(hex.length() ==3) {
            hex1 = hex.substring(0,1);
            hex2 = hex.substring(1,hex.length());
        }else {
            hex1 = hex.substring(0,2);
            hex2 = hex.substring(2,hex.length());
        }
        boolean isAutoConn = (boolean) SPUtils.getParam(mContext,Constants.AutoConnected,false);
        //        boolean isAutoDisconn = (boolean) SPUtils.getParam(mContext,Constants.AutoDisconnected,false);

        byte[] mBytes = new byte[]{BleUtils.i2b(Integer.parseInt(hex1,16))
                ,BleUtils.i2b(Integer.parseInt(hex2,16))
                ,BleUtils.i2b(DataUtils.getMonth())
                ,BleUtils.i2b(DataUtils.getDay())
                ,BleUtils.i2b(DataUtils.getHour())
                ,BleUtils.i2b(DataUtils.getMinute())
                ,BleUtils.i2b(DataUtils.getSecond())
                ,BleUtils.i2b(DataUtils.getWay())
                ,BleUtils.i2b(isAutoConn ? 1 : 0)
                ,BleUtils.i2b(isChecked ? 1 : 0)};

        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.autoUUID , mBytes, new BleWriteResponse() {
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
    public void saveTimerOff(boolean isOff) {
        SPUtils.setParam(mContext, Constants.TimerOff,isOff);
    }

    @Override
    public void saveTimerOn(boolean isOn) {
        SPUtils.setParam(mContext, Constants.TimerOn,isOn);
    }

    @Override
    public void setTimerOff(boolean isChecked, String data) {
        syTime();
        byte[] mBytes = null;
        if(isChecked) {
            mBytes = new byte[]{BleUtils.i2b(1)
                    ,BleUtils.i2b(1)
                    ,BleUtils.i2b(DataUtils.getHourFormString(data))
                    ,BleUtils.i2b(DataUtils.getMinuteFromString(data))
                    ,BleUtils.i2b(DataUtils.getSecondFromString(data))
                    ,BleUtils.i2b(7)
                    ,BleUtils.i2b(0)
                    ,BleUtils.i2b(Constants.B)
                    ,BleUtils.i2b(Constants.G)
                    ,BleUtils.i2b(Constants.R)
                    ,BleUtils.i2b(Constants.Light)};
        }else {
            mBytes = new byte[]{BleUtils.i2b(3)
                    ,BleUtils.i2b(1)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)};
        }


        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.timeUUID , mBytes, new BleWriteResponse() {
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
    public void setTimerOn(boolean isChecked, String data) {
        syTime();
        byte[] mBytes = null;
        if(isChecked) {
            mBytes = new byte[]{BleUtils.i2b(1)
                    ,BleUtils.i2b(2)
                    ,BleUtils.i2b(DataUtils.getHourFormString(data))
                    ,BleUtils.i2b(DataUtils.getMinuteFromString(data))
                    ,BleUtils.i2b(DataUtils.getSecondFromString(data))
                    ,BleUtils.i2b(7)
                    ,BleUtils.i2b(1)
                    ,BleUtils.i2b(Constants.B)
                    ,BleUtils.i2b(Constants.G)
                    ,BleUtils.i2b(Constants.R)
                    ,BleUtils.i2b(Constants.Light)};
        }else {
            mBytes = new byte[]{BleUtils.i2b(3)
                    ,BleUtils.i2b(2)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)
                    ,BleUtils.i2b(255)};
        }


        String mac = (String) SPUtils.getParam(mContext, Constants.BlueToothMac,"");
        if(!"".equals(mac)) {
            mClient.write(mac, Constants.serviceUUID, Constants.timeUUID , mBytes, new BleWriteResponse() {
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
    public void saveOffTime(String OffTime) {
        SPUtils.setParam(mContext, Constants.OffTime,OffTime);
    }

    @Override
    public void saveOnTime(String OnTime) {
        SPUtils.setParam(mContext, Constants.OnTime,OnTime);
    }

    @Override
    public boolean getAutoConn() {
        return (boolean) SPUtils.getParam(mContext,Constants.AutoConnected,false);
    }

    @Override
    public boolean getAutoDisconn() {
        return (boolean) SPUtils.getParam(mContext,Constants.AutoDisconnected,false);
    }

    @Override
    public boolean getTimerOff() {
        return (boolean) SPUtils.getParam(mContext,Constants.TimerOff,false);
    }

    @Override
    public boolean getTimerOn() {
        return (boolean) SPUtils.getParam(mContext,Constants.TimerOn,false);
    }

    @Override
    public String getOffTime() {
        return (String) SPUtils.getParam(mContext, Constants.OffTime,"");
    }

    @Override
    public String getOnTime() {
        return (String) SPUtils.getParam(mContext, Constants.OnTime,"");
    }


}
