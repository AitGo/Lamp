package com.rbu.lamphouse.user.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.user.model.RegisterModel;
import com.rbu.lamphouse.user.model.RegisterModelImpl;
import com.rbu.lamphouse.user.view.RegisterView;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:31
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RegisterPersenterImpl implements RegisterPersenter{
    private Context mContext;
    private RegisterView mView;
    private RegisterModel mModel;

    public RegisterPersenterImpl(Context context,RegisterView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new RegisterModelImpl(context);
    }

    @Override
    public void startActivity(Activity activity) {
        Intent intent = new Intent(mContext,activity.getClass());
        mContext.startActivity(intent);
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
        mView.showProgress("");
        //请求网络，获取验证码
        OkHttpUtils
                .postString()
                .url(Api.tkjn)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(new DataEntity.Builder().email(email).isRegister(1)))
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
    public void regist() {
        String email = mView.getEmail();
        String tkjn = mView.getTkjn();
        String password = mView.getPassword();
        if(email.length() <= 0) {
            mView.showToast(mContext.getString(R.string.email_empty));
            return;
        }
        if(!StringUtils.isEmail(email)) {
            mView.showToast(mContext.getString(R.string.email_error));
            return;
        }
        if(tkjn.length() <= 0) {
            mView.showToast(mContext.getString(R.string.tkjn_empty));
            return;
        }
        if(password.length() <= 0) {
            mView.showToast(mContext.getString(R.string.password_empty));
            return;
        }
        mView.showProgress("");
        LogUtils.e("json" + new Gson().toJson(new DataEntity.Builder().email(email).graphCode(tkjn).userPwd(password)));
        //请求网络
        OkHttpUtils
                .postString()
                .url(Api.regist)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(new DataEntity.Builder().email(email).graphCode(tkjn).userPwd(password)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.regist + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                        mView.cancelTimer();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.regist + "\n" + response);
                        Gson gson = new Gson();
                        mView.dismissProgress("");
                        try {
                            if(mModel.checkMsg(gson,response)) {
                                //注册成功，跳转页面
                                UserNetEntity entity = gson.fromJson(response,UserNetEntity.class);
                                mModel.saveToken(entity.getToken());
                                mModel.saveEmail(entity.getData().geteMail());
                                mView.finishActivity();
                                EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_LOGIN));
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
