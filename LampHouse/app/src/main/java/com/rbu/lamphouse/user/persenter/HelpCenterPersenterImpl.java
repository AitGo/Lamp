package com.rbu.lamphouse.user.persenter;

import android.content.Context;

import com.rbu.lamphouse.user.model.HelpCenterModel;
import com.rbu.lamphouse.user.model.HelpCenterModelImpl;
import com.rbu.lamphouse.user.view.HelpCenterView;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/14 10:46
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HelpCenterPersenterImpl implements HelpCenterPersenter {
    private Context mContext;
    private HelpCenterView mView;
    private HelpCenterModel mModel;

    public HelpCenterPersenterImpl(Context context,HelpCenterView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new HelpCenterModelImpl(context);
    }

    @Override
    public void reSizeImg() {
        mView.webViewLoadUrl(mModel.reSizeImgJaveScript());
        mView.webViewLoadUrl("javascript:ResizeImages();");

    }

    @Override
    public void shouldOverrideUrlLoading(String url) {
        mView.webViewLoadUrl(url);
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }
}
