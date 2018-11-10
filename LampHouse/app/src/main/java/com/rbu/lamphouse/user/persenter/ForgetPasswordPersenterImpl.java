package com.rbu.lamphouse.user.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.user.model.ForgetPasswordModel;
import com.rbu.lamphouse.user.model.ForgetPasswordModelImpl;
import com.rbu.lamphouse.user.view.ForgetPasswordView;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/13 17:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ForgetPasswordPersenterImpl implements ForgetPasswordPersenter {

    private Context mContext;
    private ForgetPasswordView mView;
    private ForgetPasswordModel mModel;

    public ForgetPasswordPersenterImpl(Context context,ForgetPasswordView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new ForgetPasswordModelImpl(context);
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }

    @Override
    public void getTkjn() {
        String email = mView.getEmail();
        if(email.length() <= 0) {
            mView.showToast(mContext.getString(R.string.email_empty));
            return;
        }
        if(!StringUtils.isEmail(email)) {
            mView.showToast(mContext.getString(R.string.email_error));
            return;
        }
        mView.showProgress("");
        //请求网络，获取验证码
        OkHttpUtils
                .postString()
                .url(Api.tkjn)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(new DataEntity.Builder().email(email).isRegister(0)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.tkjn + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.tkjn + "\n" + response);
                        mView.dismissProgress("");
                        Gson gson = new Gson();
                        try {
                            if (mModel.checkStateMsg(gson, response)) {
                                mView.startTimer();
                                mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());
                            } else {
                                //获取验证码失败，提示用户
                                mView.showToast(gson.fromJson(response,UserNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });
    }

    @Override
    public void confirm() {
        String email = mView.getEmail();
        String tkjn = mView.getTkjn();
        String password = mView.getPassword();
        String password_confirm = mView.getPasswordConfirm();

        if(email.length() <= 0) {
            mView.showToast(mContext.getString(R.string.email_empty));
            return;
        }
        if(!StringUtils.isEmail(email)) {
            mView.showToast(mContext.getString(R.string.email_error));
            return;
        }
        if(tkjn.length() <= 0) {
            mView.showToast(mContext.getString(R.string.email_empty));
            return;
        }
        if(password.length() <= 0) {
            mView.showToast(mContext.getString(R.string.password_empty));
            return;
        }
        if(!password.equals(password_confirm)) {
            mView.showToast(mContext.getString(R.string.password_error));
            return;
        }

        mView.showProgress("");
        //请求网络
        OkHttpUtils
                .postString()
                .url(Api.forgetPass)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(new DataEntity.Builder().email(email).password(password).confirmPassword(password_confirm).graphCode(tkjn)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.forgetPass + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                        mView.cancelTimer();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.forgetPass + "\n" + response);
                        Gson gson = new Gson();
                        mView.dismissProgress("");
                        try {
                            if(mModel.checkStateMsg(gson,response)) {
                                mView.finishActivity();
                            }else {
                                mView.showToast(gson.fromJson(response,UserNetEntity.class).getMessage());
                                mView.cancelTimer();
                            }
                        } catch (Exception e) {
                            mView.cancelTimer();
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }

                    }
                });

    }
}
