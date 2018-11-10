package com.rbu.lamphouse.user.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.user.model.UserModel;
import com.rbu.lamphouse.user.model.UserModelImpl;
import com.rbu.lamphouse.user.view.CouponActivity;
import com.rbu.lamphouse.user.view.LoginActivity;
import com.rbu.lamphouse.user.view.UserView;
import com.rbu.lamphouse.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserPersenterImpl implements UserPersenter{
    private Context mContext;
    private UserView mView;
    private UserModel mModel;

    public UserPersenterImpl(Context context,UserView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new UserModelImpl(context);
    }


    @Override
    public void startActivity(Activity activity) {
        Intent intent = new Intent(mContext,activity.getClass());
        mContext.startActivity(intent);
    }

    @Override
    public void login() {
        //在login页面保存了token
        String eMail = mModel.getEmail();
        mView.setUserName(eMail);
        mView.SetLoginVisibility(View.GONE);
        mView.SetRegistVisibility(View.GONE);
        mView.setLogouttVisibility(View.VISIBLE);
    }

    @Override
    public void logout() {
        mView.showProgress("");
        OkHttpUtils
                .get()
                .url(Api.logout)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.logout + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.logout + "\n" + response);
                        mView.dismissProgress("");
                        Gson gson = new Gson();
                        try {
                            if(mModel.checkMsg(gson,response)) {
                                // 更新界面
                                mModel.setToken("");
                                mModel.setEmail("");
                                mView.setUserName(mContext.getString(R.string.unlogin));
                                mView.SetLoginVisibility(View.VISIBLE);
                                mView.SetRegistVisibility(View.VISIBLE);
                                mView.setLogouttVisibility(View.GONE);
                                EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_LOGOUT));
                            }else {
                                //退出失败
                                mView.showToast(gson.fromJson(response,UserNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }

                    }
                });

    }

    @Override
    public void initData() {
        String token = mModel.getToken();
        if("".equals(token)) {
            mView.setUserName(mContext.getString(R.string.unlogin));
            mView.SetLoginVisibility(View.VISIBLE);
            mView.SetRegistVisibility(View.VISIBLE);
            mView.setLogouttVisibility(View.GONE);
        }else {
            //token登录，更新界面
            OkHttpUtils
                    .postString()
                    .url(Api.tokenLogin)
                    .mediaType(MediaType.parse("application/json"))
                    .content(new Gson().toJson(new DataEntity.Builder().token(mModel.getToken())))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.e("URL" + Api.tokenLogin + "\n" + e.getMessage());
                            mView.dismissProgress("");
                            mView.showToast(mContext.getString(R.string.checknet));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.e("URL" + Api.tokenLogin + "\n" + response);
                            mView.dismissProgress("");
                            Gson gson = new Gson();
                            try {
                                if (mModel.checkMsg(gson, response)) {
                                    //登录成功，更新页面
                                    UserNetEntity data = gson.fromJson(response, UserNetEntity.class);
                                    mModel.setEmail(data.getData().geteMail());
//                                    mModel.setToken(data.getToken());
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

    @Override
    public void startCoupon() {
        if(!"".equals(mModel.getToken())) {
            Intent intent = new Intent(mContext, CouponActivity.class);
            mContext.startActivity(intent);
        }else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
        }
    }
}
