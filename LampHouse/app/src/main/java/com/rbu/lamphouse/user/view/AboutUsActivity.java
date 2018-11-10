package com.rbu.lamphouse.user.view;

import android.view.View;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.AboutUsPersenter;
import com.rbu.lamphouse.user.persenter.AboutUsPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/15 11:20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AboutUsActivity extends BaseActivity implements AboutUsView, View.OnClickListener {

    private AboutUsPersenter mPersenter;
    private LinearLayout back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected void initView() {
        mPersenter = new AboutUsPersenterImpl(this,this);
        back = findViewById(R.id.about_back);

        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_back:

                break;
        }
    }


    @Override
    public void finishActivity() {
        finish();
    }
}
