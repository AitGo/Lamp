package com.rbu.lamphouse.main.view;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseFragmentActivity;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.device.view.DeviceFragment;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.home.view.HomeFragment;
import com.rbu.lamphouse.main.persenter.MainPersenter;
import com.rbu.lamphouse.main.persenter.MainPersenterImpl;
import com.rbu.lamphouse.message.view.MessageFragment;
import com.rbu.lamphouse.user.view.UserFragment;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener,MainView{

    private MainPersenter persenter;

    private LinearLayout page_home,page_device,page_message,page_user;
    private ImageView iv_home,iv_device,iv_message,iv_user;
    private AppTextView tv_home,tv_device,tv_message,tv_user;
    private FrameLayout main_fl;

    private HomeFragment mHomeFragment;
    private DeviceFragment mDeviceFragment;
    private MessageFragment mMessageFragment;
    private UserFragment mUserFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        persenter = new MainPersenterImpl(this, this);

        main_fl = (FrameLayout) findViewById(R.id.main_fl);
        page_home = (LinearLayout) findViewById(R.id.page_home);
        page_device = (LinearLayout) findViewById(R.id.page_device);
        page_message = (LinearLayout) findViewById(R.id.page_message);
        page_user = (LinearLayout) findViewById(R.id.page_user);
        iv_home = (ImageView) findViewById(R.id.page_iv_home);
        iv_device = (ImageView) findViewById(R.id.page_iv_device);
        iv_message = (ImageView) findViewById(R.id.page_iv_message);
        iv_user = (ImageView) findViewById(R.id.page_iv_user);
        tv_home = (AppTextView) findViewById(R.id.page_tv_home);
        tv_device = (AppTextView) findViewById(R.id.page_tv_device);
        tv_message = (AppTextView) findViewById(R.id.page_tv_message);
        tv_user = (AppTextView) findViewById(R.id.page_tv_user);

        page_home.setOnClickListener(this);
        page_device.setOnClickListener(this);
        page_message.setOnClickListener(this);
        page_user.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        //复制配置文件
        persenter.initConfigFile(Constants.getConfigDir(this),Constants.CONFIG_NAME);
        persenter.initColorConfigFile(Constants.getConfigDir(this),Constants.COLOR_CONFIG_NAME);
        addFragment();
        hideFragment();

        //设置tab背景
        setTabUnCheck();
        setHomeTab(true);
        //加载homefragment
        persenter.showHomeFragment(mHomeFragment);
        //设置语言
        persenter.switchLanguage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_home:
                persenter.showHomeFragment(mHomeFragment);
                break;
            case R.id.page_device:
                persenter.showDeviceFragment(mDeviceFragment);
                break;
            case R.id.page_message:
                persenter.showMessageFragment(mMessageFragment);
                break;
            case R.id.page_user:
                persenter.showUserFragment(mUserFragment);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }



    @Override
    public void setHomeTab(boolean isCheck) {
        if(isCheck) {
            iv_home.setImageResource(R.mipmap.shouye);
            tv_home.setTextColor(getResources().getColor(R.color.bg_blue));
        }else {
            iv_home.setImageResource(R.mipmap.shouye_1);
            tv_home.setTextColor(getResources().getColor(R.color.bg_gray));
        }
    }

    @Override
    public void setDeviceTab(boolean isCheck) {
        if(isCheck) {
            iv_device.setImageResource(R.mipmap.device_1);
            tv_device.setTextColor(getResources().getColor(R.color.bg_blue));
        }else {
            iv_device.setImageResource(R.mipmap.device);
            tv_device.setTextColor(getResources().getColor(R.color.bg_gray));
        }
    }

    @Override
    public void setMessageTab(boolean isCheck) {
        if(isCheck) {
            iv_message.setImageResource(R.mipmap.message_1);
            tv_message.setTextColor(getResources().getColor(R.color.bg_blue));
        }else {
            iv_message.setImageResource(R.mipmap.message);
            tv_message.setTextColor(getResources().getColor(R.color.bg_gray));
        }
    }

    @Override
    public void setUserTab(boolean isCheck) {
        if(isCheck) {
            iv_user.setImageResource(R.mipmap.user_1);
            tv_user.setTextColor(getResources().getColor(R.color.bg_blue));
        }else {
            iv_user.setImageResource(R.mipmap.user);
            tv_user.setTextColor(getResources().getColor(R.color.bg_gray));
        }
    }

    @Override
    public void setTabUnCheck() {
        iv_home.setImageResource(R.mipmap.shouye_1);
        tv_home.setTextColor(getResources().getColor(R.color.bg_gray));
        iv_device.setImageResource(R.mipmap.device);
        tv_device.setTextColor(getResources().getColor(R.color.bg_gray));
        iv_message.setImageResource(R.mipmap.message);
        tv_message.setTextColor(getResources().getColor(R.color.bg_gray));
        iv_user.setImageResource(R.mipmap.user);
        tv_user.setTextColor(getResources().getColor(R.color.bg_gray));
    }

    @Override
    public void switchFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fl,fragment);
        transaction.commit();
    }

    @Override
    public void showFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public void addFragment() {
        transaction = fragmentManager.beginTransaction();
        if(mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.main_fl,mHomeFragment);
        }
        if(mDeviceFragment == null) {
            mDeviceFragment = new DeviceFragment();
            transaction.add(R.id.main_fl,mDeviceFragment);
        }
        if(mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
            transaction.add(R.id.main_fl,mMessageFragment);
        }
        if(mUserFragment == null) {
            mUserFragment = new UserFragment();
            transaction.add(R.id.main_fl,mUserFragment);
        }

        transaction.commit();
    }

    @Override
    public void hideFragment() {
        transaction = fragmentManager.beginTransaction();
        if(mHomeFragment != null && mHomeFragment.isVisible()) {
            transaction.hide(mHomeFragment);
        }
        if(mDeviceFragment != null) {
            transaction.hide(mDeviceFragment);
        }
        if(mMessageFragment != null) {
            transaction.hide(mMessageFragment);
        }
        if(mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
        transaction.commit();
    }

    @Override
    public void removeAllActivity() {
        finish();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public Fragment getHomeFragment() {
        return mHomeFragment;
    }

    @Override
    public Fragment getDeviceFragment() {
        return mDeviceFragment;
    }

    @Override
    public Fragment getMessageFragment() {
        return mMessageFragment;
    }

    @Override
    public Fragment getUserFragment() {
        return mUserFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            persenter.exit();
        }
        return true;
    }


}
