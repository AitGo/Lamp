package com.rbu.lamphouse.user.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserModelImpl implements UserModel{

    private Context mContext;
    public UserModelImpl(Context context) {
        this.mContext = context;
    }


    @Override
    public String getEmail() {
        return (String) SPUtils.getParam(mContext, Constants.email,"");
    }

    @Override
    public String getToken() {
        return (String) SPUtils.getParam(mContext, Constants.token,"");
    }


    @Override
    public void setToken(String token) {
        SPUtils.setParam(mContext,Constants.token,token);
    }

    @Override
    public void setEmail(String email) {
        SPUtils.setParam(mContext,Constants.email,email);
    }

    @Override
    public boolean checkMsg(Gson gson,String response) throws JsonSyntaxException {
        UserNetEntity userData = gson.fromJson(response, UserNetEntity.class);
        if(userData.getStatus() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }
}
