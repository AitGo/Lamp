package com.rbu.lamphouse.user.view;


import android.support.v4.app.Fragment;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 14:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface CouponView {

    void setUnusedValue(String value);

    void setUsedValue(String value);

    void setOverdueValue(String value);

    String getUnusedValue();

    String getUsedValue();

    String getOverdueValue();

    void finishActivity();

    void setUnusedTab();

    void setUsedTab();

    void setOverdueTab();

    void switchFragment(Fragment fragment);

    void showToast(String msg);

    void showEditDialog();

    void showProgress(String msg);

    void dismissProgress(String msg);
}
