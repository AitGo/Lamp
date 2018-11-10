package com.rbu.lamphouse.user.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.utils.SPUtils;
import com.rbu.lamphouse.utils.StringUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:40
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoginModelImpl implements LoginModel {
    private Context mContext;

    public LoginModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void saveEmail(String email) {
        SPUtils.setParam(mContext, Constants.email,email);
    }

    @Override
    public void saveToken(String token) {
        SPUtils.setParam(mContext,Constants.token,token);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.setParam(mContext,Constants.password,password);
    }

    @Override
    public boolean checkEmail(String email) {
        return StringUtils.isEmail(email);
    }

    @Override
    public boolean checkPassword(String password) {
        return StringUtils.isPassword(password);
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
