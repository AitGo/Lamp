package com.rbu.lamphouse.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.diagnose.CouponEntity;
import com.rbu.lamphouse.diagnose.MessageEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 12:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CouponAdapter extends BaseQuickAdapter<CouponEntity,BaseViewHolder> {
    private boolean isVisibility = true;
    public CouponAdapter(int layoutResId, @Nullable List<CouponEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponEntity item) {
        helper.setText(R.id.item_coupon_code,item.getCouponCode());
        helper.setText(R.id.item_coupon_start,item.getStartTime());
        helper.setText(R.id.item_coupon_end,item.getEndTime());
        helper.setText(R.id.item_coupon_used,item.getUsedTime());
        helper.setText(R.id.item_coupon_money,"$" + item.getMoney());
        helper.setVisible(R.id.item_coupon_copy,isVisibility);
        helper.addOnClickListener(R.id.item_coupon_copy);
    }

    public void setCopyBtnVisibility(boolean visibility) {
        this.isVisibility = visibility;
    }
}
