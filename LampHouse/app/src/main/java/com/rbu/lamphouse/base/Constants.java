package com.rbu.lamphouse.base;

import android.content.Context;
import android.os.Environment;

import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.diagnose.ColorRGB;
import com.rbu.lamphouse.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 12:12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Constants {


    public static int DEBUGLEVEL = LogUtils.LEVEL_ALL; //打开日志开关

    //ColorConfig配置文件的type
    public static String COLOR_TYPE_MAIN = "main";
    public static String COLOR_TYPE_SETTING = "setting";
    //SP 配置文件的属性
    public static String Language = "language";
    public static String AutoConnected = "autoconn";
    public static String AutoDisconnected = "autodisconn";
    public static String BlueToothName = "bluename";
    public static String BlueToothMac = "bluemac";
    public static String TimerOff = "timeroff";
    public static String TimerOn = "timeron";
    public static String OffTime = "offtime";
    public static String OnTime = "ontime";
    //SP userToken
    public static String token = "token";
    public static int successState = 1;
    public static int failState = 0;

    //config文件目录
    public static String getConfigDir(Context mContext) {
        return mContext.getFilesDir() + File.separator;
    }
    //ColorConfig文件名
    public static String COLOR_CONFIG_NAME = "ColorConfig.xml";
    public static String CONFIG_NAME = "config.xml";

    //ColorConfig配置
    public static ArrayList<ColorConfig> colorConfigList = new ArrayList<>();

    //颜色配置
    public static Integer[] home_r = {255,255,255,84,76,0,234,255};
    public static Integer[] home_g = {255,206,56,56,210,247,56,112};
    public static Integer[] home_b = {255,41,56,255,77,238,254,56};

    public static List<ColorRGB> getHomeColor() {
        List<ColorRGB> list = new ArrayList<>();
        for(int i = 0; i < home_r.length; i++) {
            ColorRGB rgb = new ColorRGB();
            rgb.setR(home_r[i]);
            rgb.setG(home_g[i]);
            rgb.setB(home_b[i]);
            list.add(rgb);
        }
        return list;
    }


    //最终设置灯箱的颜色
    public static int R;
    public static int G;
    public static int B;
    public static int Light = 100;

    public static UUID serviceUUID = UUID.fromString("0000ffb0-0000-1000-8000-00805f9b34fb");
    public static UUID colorUUID = UUID.fromString("0000ffb2-0000-1000-8000-00805f9b34fb");
    public static UUID autoUUID = UUID.fromString("0000ffb3-0000-1000-8000-00805f9b34fb");
    public static UUID timeUUID = UUID.fromString("0000ffb4-0000-1000-8000-00805f9b34fb");

    public static String BluetoothLightName = "BLE-LED";

    public static int BluetoothState_Connecting = 2;
    public static int BluetoothState_DeviceConnecting = 0;

    public static String email = "email";
    public static String password = "password";

}
