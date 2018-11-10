package com.rbu.lamphouse.utils;

import android.graphics.Color;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/1 15:20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ColorUtils {

    public static String R = "r";
    public static String G = "g";
    public static String B = "b";

    public static int getRGBFromColor(int color,String type) {

        int a = (color >>> 24);
        int r = (color >>  16) & 0xFF;
        int g = (color >>   8) & 0xFF;
        int b = (color)        & 0xFF;

        String rupStr=Integer.toHexString(r);
        String gupStr=Integer.toHexString(g);
        String bupStr=Integer.toHexString(b);

        rupStr = rupStr.equals("0") ? rupStr="00" : rupStr;
        gupStr = gupStr.equals("0") ? gupStr="00" : gupStr;
        bupStr = bupStr.equals("0") ? bupStr="00" : bupStr;
        String colorUpStr=rupStr+gupStr+bupStr;    //十六进制的颜色字符串。

        if(R.equals(type)) {
            return r;
        }else if(G.equals(type)) {
            return g;
        }else if(B.equals(type)){
            return b;
        }else {
            return 0;
        }
    }

    public static int getColorFromRGB(int r,int g,int b) {
        return  Color.rgb(r,g,b);
    }

}
