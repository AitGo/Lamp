package com.rbu.lamphouse.setting.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.ProfilesGridAdapter;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.setting.persenter.ProfilesPersenter;
import com.rbu.lamphouse.setting.persenter.ProfilesPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 16:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProfilesActivity extends BaseActivity implements ProfilesView, AdapterView.OnItemClickListener, View.OnClickListener {
    private ProfilesPersenter mPersenter;

    private GridView mGridView;
    private ProfilesGridAdapter mAdapter;

    private LinearLayout profiles_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profiles;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        mPersenter = new ProfilesPersenterImpl(this,this);

        profiles_back = findViewById(R.id.profiles_back);
        mGridView = findViewById(R.id.profiles_gridview);

        profiles_back.setOnClickListener(this);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        mAdapter = new ProfilesGridAdapter(this, Constants.colorConfigList);
        mGridView.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UIChangedEvent event) {
        if(event.getMsg() == UIChangedEvent.MSG_ADD_PROFILES) {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == Constants.colorConfigList.size()) {
            //打开添加情景页面
            mPersenter.startActivity(new AddProfilesActivity());
        }else {
            //切换颜色
            mPersenter.setLampColor(Constants.colorConfigList.get(position));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profiles_back:
                mPersenter.finishActivity();
                break;
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
