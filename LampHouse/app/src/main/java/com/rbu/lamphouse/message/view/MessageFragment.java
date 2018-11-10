package com.rbu.lamphouse.message.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.device.view.DeviceFragment;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.home.view.HomeFragment;
import com.rbu.lamphouse.message.persenter.MessagePersenter;
import com.rbu.lamphouse.message.persenter.MessagePersenterImpl;
import com.rbu.lamphouse.user.view.UserFragment;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppButton;
import com.rbu.lamphouse.widget.AppTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 10:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MessageFragment extends BaseFragment implements MessageView, View.OnClickListener {
    private MessagePersenter mPersenter;
    private View                inflate;
    private FrameLayout         message_fl;
    private  FragmentManager     fragmentManager;
    private  FragmentTransaction transaction;

    private RelativeLayout unRead_tab,read_tab;
    private AppTextView unRead_title,read_title;
    private View unRead_line,read_line;
    private LinearLayout message_edit,message_all,message_done;
    private AppButton btn_delete;
    private UnReadFragment mUnReadFragment;
    private ReadFragment mReadFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View inflate) {
        mPersenter = new MessagePersenterImpl(getContext(),this);
        this.inflate = inflate;
        message_fl = inflate.findViewById(R.id.message_fl);

        unRead_tab = inflate.findViewById(R.id.message_unread_tab);
        read_tab = inflate.findViewById(R.id.message_read_tab);
        unRead_title = inflate.findViewById(R.id.message_unread_title);
        read_title = inflate.findViewById(R.id.message_read_title);
        unRead_line = inflate.findViewById(R.id.message_unread_line);
        read_line = inflate.findViewById(R.id.message_read_line);

        message_edit = inflate.findViewById(R.id.message_edit);
        message_all = inflate.findViewById(R.id.message_all);
        message_done = inflate.findViewById(R.id.message_done);
        btn_delete = inflate.findViewById(R.id.message_delete);

        unRead_tab.setOnClickListener(this);
        read_tab.setOnClickListener(this);
        message_edit.setOnClickListener(this);
        message_all.setOnClickListener(this);
        message_done.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        fragmentManager = getChildFragmentManager();
        transaction = fragmentManager.beginTransaction();
        addFragment();
        hideFragment();
        mPersenter.showUnReadFragment(mUnReadFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_unread_tab:
                mPersenter.showUnReadFragment(mUnReadFragment);
                break;
            case R.id.message_read_tab:
                mPersenter.showReadFragment(mReadFragment);
                break;
            case R.id.message_edit:
                mPersenter.edit();
                break;

            case R.id.message_all:
                mPersenter.all();
                break;

            case R.id.message_done:
                mPersenter.done();
                break;

            case R.id.message_delete:
                mPersenter.delete();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
    }

    @Override
    public void switchFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.message_fl,fragment);
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
        if(mUnReadFragment == null) {
            mUnReadFragment = new UnReadFragment();
            transaction.add(R.id.message_fl,mUnReadFragment);
        }
        if(mReadFragment == null) {
            mReadFragment = new ReadFragment();
            transaction.add(R.id.message_fl,mReadFragment);
        }
        transaction.commit();
    }

    @Override
    public void hideFragment() {
        transaction = fragmentManager.beginTransaction();
        if(mUnReadFragment != null) {
            transaction.hide(mUnReadFragment);
        }
        if(mReadFragment != null) {
            transaction.hide(mReadFragment);
        }
        transaction.commit();
    }

    @Override
    public void chooseUnReadTab() {
        unRead_title.setTextColor(getResources().getColor(R.color.bg_blue));
        unRead_line.setBackgroundColor(getResources().getColor(R.color.bg_blue));
        read_title.setTextColor(getResources().getColor(R.color.text_black));
        read_line.setBackgroundColor(getResources().getColor(R.color.bg_gray));
    }

    @Override
    public void chooseReadTab() {
        read_title.setTextColor(getResources().getColor(R.color.bg_blue));
        read_line.setBackgroundColor(getResources().getColor(R.color.bg_blue));
        unRead_title.setTextColor(getResources().getColor(R.color.text_black));
        unRead_line.setBackgroundColor(getResources().getColor(R.color.bg_gray));
    }

    @Override
    public void setEditVisibility(int visibility) {
        message_edit.setVisibility(visibility);
    }

    @Override
    public void setAllVisibility(int visibility) {
        message_all.setVisibility(visibility);
    }

    @Override
    public void setDoneVisibility(int visibility) {
        message_done.setVisibility(visibility);
    }

    @Override
    public void setDeleteVisibility(int visibility) {
        btn_delete.setVisibility(visibility);
    }

    @Override
    public Fragment getUnReadFragment() {
        return mUnReadFragment;
    }

    @Override
    public Fragment getReadFragment() {
        return mReadFragment;
    }


}
