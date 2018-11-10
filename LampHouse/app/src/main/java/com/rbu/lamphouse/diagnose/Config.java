package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 17:26
 * @描述 ${配置文件实体类}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class Config {

    private String Language;
    private String AutoConnected;
    private String AutoDisconnected;
    private String BlueToothName;
    private String BlueToothMac;
    private String TimerOff;
    private String TimerOn;
    private String OffTime;
    private String OnTime;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getAutoConnected() {
        return AutoConnected;
    }

    public void setAutoConnected(String autoConnected) {
        AutoConnected = autoConnected;
    }

    public String getAutoDisconnected() {
        return AutoDisconnected;
    }

    public void setAutoDisconnected(String autoDisconnected) {
        AutoDisconnected = autoDisconnected;
    }

    public String getBlueToothName() {
        return BlueToothName;
    }

    public void setBlueToothName(String blueToothName) {
        BlueToothName = blueToothName;
    }

    public String getBlueToothMac() {
        return BlueToothMac;
    }

    public void setBlueToothMac(String blueToothMac) {
        BlueToothMac = blueToothMac;
    }

    public String getTimerOff() {
        return TimerOff;
    }

    public void setTimerOff(String timerOff) {
        TimerOff = timerOff;
    }

    public String getTimerOn() {
        return TimerOn;
    }

    public void setTimerOn(String timerOn) {
        TimerOn = timerOn;
    }

    public String getOffTime() {
        return OffTime;
    }

    public void setOffTime(String offTime) {
        OffTime = offTime;
    }

    public String getOnTime() {
        return OnTime;
    }

    public void setOnTime(String onTime) {
        OnTime = onTime;
    }
}
