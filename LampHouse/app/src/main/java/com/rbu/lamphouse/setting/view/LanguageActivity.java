package com.rbu.lamphouse.setting.view;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.LanguageListAdapter;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.diagnose.Language;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.setting.persenter.LanguagePersenter;
import com.rbu.lamphouse.setting.persenter.LanguagePersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 17:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LanguageActivity extends BaseActivity implements LanguageView, View.OnClickListener, AdapterView.OnItemClickListener {
    private LanguagePersenter mPersenter;
    private LinearLayout language_back;
    private ListView language_lv;
    private List<Language> languageList = new ArrayList<Language>();
    private LanguageListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    protected void initView() {
        mPersenter = new LanguagePersenterImpl(this,this);

        language_back = findViewById(R.id.language_back);
        language_lv = findViewById(R.id.language_lv);

        language_back.setOnClickListener(this);
        language_lv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        initListData(languageList);
        mAdapter = new LanguageListAdapter(this,languageList);
        language_lv.setAdapter(mAdapter);
    }

    @Override
    public void initListData(List<Language> list) {
        mPersenter.initListData(list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.language_back:
                mPersenter.finishActivity();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mPersenter.setState(languageList,position);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
