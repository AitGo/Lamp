package com.rbu.lamphouse.setting.persenter;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 15:33
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface AddProfilesPersenter {

    void finishActivity();

    void addColorConfig(String input);

    void saveColorConfig();

    void setRGB(int index);

    void setEditText(int color);
}
