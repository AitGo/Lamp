package com.rbu.lamphouse.user.persenter;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 15:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface CouponPersenter {

    void finishActivity();

    void addCoupon(String input);

    void showUnusedFragment();

    void showUsedFragment();

    void showOverdueFragment();

    void initData();
}
