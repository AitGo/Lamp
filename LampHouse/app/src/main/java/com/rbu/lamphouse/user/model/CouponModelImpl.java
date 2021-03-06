package com.rbu.lamphouse.user.model;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 14:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CouponModelImpl implements CouponModel {

    private Context mContext;

    public CouponModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean checkMsg(Gson gson, String response) {
        UserNetEntity userData = gson.fromJson(response, UserNetEntity.class);
        if(userData.getStatus() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkStateMsg(Gson gson, String response) {
        UserNetEntity userData = gson.fromJson(response, UserNetEntity.class);
        if(userData.getState() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getToken() {
        return (String) SPUtils.getParam(mContext,Constants.token,"");
    }
}
