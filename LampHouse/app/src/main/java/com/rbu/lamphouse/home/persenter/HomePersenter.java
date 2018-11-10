package com.rbu.lamphouse.home.persenter;

import android.app.Activity;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface HomePersenter {

    void startSettingActivity(Activity activity);

    void setEditText(int color);

    void setLampRGB(int color);

    void setLampRGB();

    void setLampLight(int light);

    void setRGB(int index);

    void RChanged(CharSequence s);

    void GChanged(CharSequence s);

    void BChanged(CharSequence s);

    void setState(boolean newstate);

    void checkLamp();

    void lampOFF();

    void lampON();
}
