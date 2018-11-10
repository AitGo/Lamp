package com.rbu.lamphouse.home.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.MonthDisplayHelper;

import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.home.model.HomeModel;
import com.rbu.lamphouse.home.model.HomeModelImpl;
import com.rbu.lamphouse.home.view.HomeFragment;
import com.rbu.lamphouse.home.view.HomeView;
import com.rbu.lamphouse.main.view.MainActivity;
import com.rbu.lamphouse.setting.view.SettingActivity;
import com.rbu.lamphouse.utils.ColorUtils;
import com.rbu.lamphouse.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomePersenterImpl implements HomePersenter {

    private Context mContext;
    private HomeView mView;
    private HomeModel mModel;
    private boolean state = false;



    public HomePersenterImpl(Context mContext,HomeView mView) {
        this.mContext = mContext;
        this.mView = mView;
        mModel = new HomeModelImpl(mContext);
    }

    @Override
    public void startSettingActivity(Activity activity) {
        Intent intent = new Intent(mContext,activity.getClass());
        mContext.startActivity(intent);
    }

    @Override
    public void setEditText(int color) {

        mView.setEt_R(mModel.getRGBFromColor(color, ColorUtils.R));
        mView.setEt_G(mModel.getRGBFromColor(color, ColorUtils.G));
        mView.setEt_B(mModel.getRGBFromColor(color, ColorUtils.B));
    }

    @Override
    public void setLampRGB(int color) {

        mModel.saveR(mModel.getRGBFromColor(color,ColorUtils.R));
        mModel.saveG(mModel.getRGBFromColor(color,ColorUtils.G));
        mModel.saveB(mModel.getRGBFromColor(color,ColorUtils.B));
        //判断状态,防止edittext改变时重复更改颜色，造成灯箱颜色卡顿
        state = true;
        mView.lostFocus();
        mModel.setLampRGB();
    }

    @Override
    public void setLampRGB() {
        if(!"".equals(mModel.getBluetoothMac())) {
            if(mModel.isBluetoothConn(mModel.getBluetoothMac()) == Constants.BluetoothState_Connecting) {
                mModel.setLampRGB();
            }
        }
    }

    @Override
    public void setLampLight(int light) {
        mModel.setLampLight(light);
        mModel.setLampRGB();
    }

    @Override
    public void setRGB(int index) {

        mModel.saveR(Constants.home_r[index]);
        mModel.saveG(Constants.home_g[index]);
        mModel.saveB(Constants.home_b[index]);
        mView.setEt_R(Constants.R);
        mView.setEt_G(Constants.G);
        mView.setEt_B(Constants.B);
    }

    @Override
    public void RChanged(CharSequence s) {
        if(s.length() > 0) {
            int r = Integer.valueOf(s.toString());
            if( r <= 255) {
                mModel.saveR(r);
                //读取状态,防止取色器改变时重复更改颜色，造成灯箱颜色卡顿
                if(!state) {
                    mModel.setLampRGB();
                }

            }
        }
    }

    @Override
    public void GChanged(CharSequence s) {
        if(s.length() > 0) {
            int g = Integer.valueOf(s.toString());
            if( g <= 255) {
                mModel.saveG(g);
                if(!state) {
                    mModel.setLampRGB();
                }
            }
        }
    }

    @Override
    public void BChanged(CharSequence s) {
        if(s.length() > 0) {
            int b = Integer.valueOf(s.toString());
            if( b <= 255) {
                mModel.saveB(b);
                if(!state) {
                    mModel.setLampRGB();
                }
            }
        }
    }

    @Override
    public void setState(boolean newstate) {
        this.state = newstate;
    }

    @Override
    public void checkLamp() {
        mModel.checkLamp();
    }

    @Override
    public void lampOFF() {
        mModel.lampOff();
    }

    @Override
    public void lampON() {
        mModel.lampOn();
    }


}
