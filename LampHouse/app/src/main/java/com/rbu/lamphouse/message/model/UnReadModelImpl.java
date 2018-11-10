package com.rbu.lamphouse.message.model;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.MsgNetEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:56
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UnReadModelImpl implements UnReadModel {
    private Context mContext;
    public UnReadModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public String getToken() {
        return (String) SPUtils.getParam(mContext,Constants.token,"");
    }

    @Override
    public boolean checkMsg(Gson gson, String response) {
        MsgNetEntity data = gson.fromJson(response, MsgNetEntity.class);
        if(data.getStatus() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkStateMsg(Gson gson, String response) {
        MsgNetEntity data = gson.fromJson(response, MsgNetEntity.class);
        if(data.getState() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }
}
