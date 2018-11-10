package com.rbu.lamphouse.user.view.coupon;

import com.rbu.lamphouse.adapter.CouponAdapter;
import com.rbu.lamphouse.diagnose.CouponEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 16:43
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface OverdueView {
    void notifyMsgList(List<CouponEntity> data,boolean isClear);

    void showToast(String msg);

    void showProgress(String msg);

    void dismissProgress(String msg);

    void addPageNum();

    void clearPageNum();

    CouponAdapter getAdapter();
}
