package com.rbu.lamphouse.user.persenter;

import android.content.Context;

import com.rbu.lamphouse.user.model.AboutUsModel;
import com.rbu.lamphouse.user.model.AboutUsModelImpl;
import com.rbu.lamphouse.user.view.AboutUsView;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/15 11:22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AboutUsPersenterImpl implements AboutUsPersenter {

    private Context mContext;
    private AboutUsModel mModel;
    private AboutUsView mView;

    public AboutUsPersenterImpl(Context context,AboutUsView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new AboutUsModelImpl();
    }
}
