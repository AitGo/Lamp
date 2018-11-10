package com.rbu.lamphouse.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.utils.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 9:28
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements BaseView {

    public  FragmentManager     fragmentManager;
    public  FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId()); //设置布局id
        //状态栏颜色设置 21（5.0以上）
        StatusBarUtils.setWindowStatusBarColor(this, R.color.headbg);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initView();  //初始化view
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        initData();  //setData

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 初始化布局文件,设置布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }

}
