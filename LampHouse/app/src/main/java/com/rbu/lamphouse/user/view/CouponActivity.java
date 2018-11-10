package com.rbu.lamphouse.user.view;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.base.BaseFragmentActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.CouponPersenter;
import com.rbu.lamphouse.user.persenter.CouponPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppTextView;
import com.rbu.lamphouse.widget.LampEditDialog;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.UserDataHandler;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 14:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CouponActivity extends BaseFragmentActivity implements CouponView, View.OnClickListener {
    private CouponPersenter mPersenter;
    private LinearLayout back,add,unused,used,overdue;
    private AppTextView unused_value,used_value,overdue_value,unused_name,used_name,overdue_name;
    private LampEditDialog lampEditDialog;
    private LampProgressDialog mProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initView() {
        mPersenter = new CouponPersenterImpl(this,this);

        back = findViewById(R.id.coupon_back);
        add = findViewById(R.id.coupon_add);
        unused = findViewById(R.id.coupon_unused);
        used = findViewById(R.id.coupon_used);
        overdue = findViewById(R.id.coupon_overdue);
        unused_value = findViewById(R.id.coupon_unused_value);
        used_value = findViewById(R.id.coupon_used_value);
        overdue_value = findViewById(R.id.coupon_overdue_value);
        unused_name = findViewById(R.id.coupon_unused_name);
        used_name = findViewById(R.id.coupon_used_name);
        overdue_name = findViewById(R.id.coupon_overdue_name);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
        unused.setOnClickListener(this);
        used.setOnClickListener(this);
        overdue.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mPersenter.initData();
        mPersenter.showUnusedFragment();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coupon_back:
                mPersenter.finishActivity();
                break;
            case R.id.coupon_add:
                showEditDialog();
                break;
            case R.id.coupon_unused:
                mPersenter.showUnusedFragment();
                break;
            case R.id.coupon_used:
                mPersenter.showUsedFragment();
                break;
            case R.id.coupon_overdue:
                mPersenter.showOverdueFragment();
                break;
        }
    }

    @Override
    public void switchFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.coupon_fl,fragment);
        transaction.commit();
    }


    @Override
    public void setUnusedValue(String value) {
        unused_value.setText(value + "USD");
    }

    @Override
    public void setUsedValue(String value) {
        used_value.setText(value + "USD");
    }

    @Override
    public void setOverdueValue(String value) {
        overdue_value.setText(value + "USD");
    }

    @Override
    public String getUnusedValue() {
        return unused_value.getText().toString().trim().replace("USD","");
    }

    @Override
    public String getUsedValue() {
        return used_value.getText().toString().trim().replace("USD","");
    }

    @Override
    public String getOverdueValue() {
        return overdue_value.getText().toString().trim().replace("USD","");
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setUnusedTab() {
        unused_name.setTextColor(getResources().getColor(R.color.text_white));
        unused_value.setTextColor(getResources().getColor(R.color.text_white));
        unused.setBackgroundResource(R.drawable.bg_choose);

        used_name.setTextColor(getResources().getColor(R.color.text_black));
        used_value.setTextColor(getResources().getColor(R.color.text_black));
        used.setBackgroundResource(R.drawable.bg_unchoose);

        overdue_name.setTextColor(getResources().getColor(R.color.text_black));
        overdue_value.setTextColor(getResources().getColor(R.color.text_black));
        overdue.setBackgroundResource(R.drawable.bg_unchoose);
    }

    @Override
    public void setUsedTab() {
        used_name.setTextColor(getResources().getColor(R.color.text_white));
        used_value.setTextColor(getResources().getColor(R.color.text_white));
        used.setBackgroundResource(R.drawable.bg_choose);

        unused_name.setTextColor(getResources().getColor(R.color.text_black));
        unused_value.setTextColor(getResources().getColor(R.color.text_black));
        unused.setBackgroundResource(R.drawable.bg_unchoose);

        overdue_name.setTextColor(getResources().getColor(R.color.text_black));
        overdue_value.setTextColor(getResources().getColor(R.color.text_black));
        overdue.setBackgroundResource(R.drawable.bg_unchoose);
    }

    @Override
    public void setOverdueTab() {
        overdue_name.setTextColor(getResources().getColor(R.color.text_white));
        overdue_value.setTextColor(getResources().getColor(R.color.text_white));
        overdue.setBackgroundResource(R.drawable.bg_choose);

        unused_name.setTextColor(getResources().getColor(R.color.text_black));
        unused_value.setTextColor(getResources().getColor(R.color.text_black));
        unused.setBackgroundResource(R.drawable.bg_unchoose);

        used_name.setTextColor(getResources().getColor(R.color.text_black));
        used_value.setTextColor(getResources().getColor(R.color.text_black));
        used.setBackgroundResource(R.drawable.bg_unchoose);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void showEditDialog() {
        LampEditDialog.Builder builder = new LampEditDialog.Builder(this);
        lampEditDialog = builder.setTitle(getString(R.string.amazon_pono))
                .setEditHint(getString(R.string.input_amazon_pono))
                .setPositiveButton(getString(R.string.confirm), new LampEditDialog.ConfirmListener() {
                    @Override
                    public void onClick(String input) {

                        mPersenter.addCoupon(input);
                        lampEditDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lampEditDialog.dismiss();
                    }
                })
                .create();
        lampEditDialog.show();
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new LampProgressDialog(this,msg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress(String msg) {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
