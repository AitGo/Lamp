package com.rbu.lamphouse.setting.persenter;

import android.app.Activity;

import com.rbu.lamphouse.diagnose.ColorConfig;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 16:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface ProfilesPersenter {

    void setLampColor(ColorConfig colorConfig);

    void startActivity(Activity activity);

    void finishActivity();
}
