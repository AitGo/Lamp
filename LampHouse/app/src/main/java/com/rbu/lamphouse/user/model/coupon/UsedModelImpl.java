package com.rbu.lamphouse.user.model.coupon;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.CouponNetEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 17:38
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UsedModelImpl implements UsedModel {
    private Context mContext;
    public UsedModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean checkMsg(Gson gson, String response) throws JsonSyntaxException {
        CouponNetEntity data = gson.fromJson(response, CouponNetEntity.class);
        if(data.getStatus() == Constants.successState) {
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
