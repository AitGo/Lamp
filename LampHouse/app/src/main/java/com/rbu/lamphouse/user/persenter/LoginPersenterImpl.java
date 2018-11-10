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
import com.rbu.lamphouse.user.model.LoginModel;
import com.rbu.lamphouse.user.model.LoginModelImpl;
import com.rbu.lamphouse.user.view.LoginView;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;


import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:40
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoginPersenterImpl implements LoginPersenter {
    private Context    mContext;
    private LoginView  mView;
    private LoginModel mModel;

    public LoginPersenterImpl(Context context, LoginView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new LoginModelImpl(context);
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

    @Override
    public void login() {
        String email = mView.getEmail();
        String password = mView.getPassword();
        if (email.length() <= 0) {
            mView.showToast(mContext.getString(R.string.email_empty));
            return;
        }
        if (!StringUtils.isEmail(email)) {
            mView.showToast(mContext.getString(R.string.email_error));
            return;
        }
        if (password.length() <= 0) {
            mView.showToast(mContext.getString(R.string.password_empty));
            return;
        }
        mView.showProgress("");

        OkHttpUtils
                .postString()
                .url(Api.emailLogin)
                .mediaType(MediaType.parse("application/json"))
                .content(new Gson().toJson(new DataEntity.Builder().eMail(email).password(password)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.emailLogin + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.emailLogin + "\n" + response);
                        mView.dismissProgress("");
                        Gson gson = new Gson();
                        try {
                            if (mModel.checkMsg(gson, response)) {
                                //登录成功，跳转页面
                                UserNetEntity data = gson.fromJson(response,UserNetEntity.class);
                                mModel.saveEmail(data.getData().geteMail());
                                mModel.saveToken(data.getToken());
                                mView.finishActivity();
                                EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_LOGIN));
                            } else {
                                //登录失败，提示用户
                                mView.showToast(gson.fromJson(response,UserNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });

    }


}
