package com.rbu.lamphouse.setting.view;

import android.widget.Switch;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 15:03
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface SettingView {

    void showToast(String msg);

    void setTimerOffText(String time);

    void setTimerOnText(String time);

    String getTimerOffText();

    String getTimerOnText();

    boolean getTimerOffChecked();

    boolean getTimerOnChecked();

    boolean getAutoConnChecked();

    boolean getAutoDisConnChecked();

    void showTimeOffPicker();

    void showTimeOnPicker();

    void finishActivity();

    void setTimerOffSwitch(boolean isClick);

    void setTimerOnSwitch(boolean isClick);

    void setAutoConnSwitch(boolean isClick);

    void setAutoDisConnSwitch(boolean isClick);

    void initTimePicker();

}
