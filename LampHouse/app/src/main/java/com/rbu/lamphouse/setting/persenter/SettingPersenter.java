package com.rbu.lamphouse.setting.persenter;

import android.app.Activity;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 15:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface SettingPersenter {

    void startActivity(Activity activity);

    void autoConnSwitch(boolean isChecked);

    void autoDisConnSwitch(boolean isChecked);

    void timerOffSwitch(boolean isChecked);

    void timerOnSwitch(boolean isChecked);

    void timerOffClick();

    void timerOnClick();

    void setTimerOffText(String time);

    void setTimerOnText(String time);

    void initTimeTextData();

    void initTimePicker();

    void initSwitch();

    void finishActivity();
}
