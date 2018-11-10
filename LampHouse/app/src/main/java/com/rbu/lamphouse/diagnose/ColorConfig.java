package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 11:11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ColorConfig {
    private String Name;//配置项名称
    private String R;
    private String G;
    private String B;
    private String Light;//灯箱亮度
//    private String Type;//配置类型，main：主界面中的配置 setting：设置中的情景模式,添加只能添加到情景模式中

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getLight() {
        return Light;
    }

    public void setLight(String light) {
        Light = light;
    }

//    public String getType() {
//        return Type;
//    }
//
//    public void setType(String type) {
//        Type = type;
//    }
}
