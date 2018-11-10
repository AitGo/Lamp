package com.rbu.lamphouse.setting.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.setting.model.ProfilesModel;
import com.rbu.lamphouse.setting.model.ProfilesModelImpl;
import com.rbu.lamphouse.setting.view.AddProfilesActivity;
import com.rbu.lamphouse.setting.view.ProfilesView;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 16:31
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProfilesPersenterImpl implements ProfilesPersenter{

    private Context mContext;
    private ProfilesView mView;
    private ProfilesModel mModel;

    public ProfilesPersenterImpl(Context context,ProfilesView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new ProfilesModelImpl(context);
    }

    @Override
    public void setLampColor(ColorConfig colorConfig) {
        mModel.saveR(Integer.valueOf(colorConfig.getR()));
        mModel.saveG(Integer.valueOf(colorConfig.getG()));
        mModel.saveB(Integer.valueOf(colorConfig.getB()));
        mModel.saveLight(Integer.valueOf(colorConfig.getLight()));
        mModel.setLampRGB();
    }

    @Override
    public void startActivity(Activity activity) {
        Intent intent = new Intent(mContext, activity.getClass());
        mContext.startActivity(intent);
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }
}
