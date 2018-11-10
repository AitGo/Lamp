package com.rbu.lamphouse.user.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.AddCouponEntity;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.user.model.CouponModel;
import com.rbu.lamphouse.user.model.CouponModelImpl;
import com.rbu.lamphouse.user.view.CouponView;
import com.rbu.lamphouse.user.view.coupon.OverdueFragment;
import com.rbu.lamphouse.user.view.coupon.UnusedFragment;
import com.rbu.lamphouse.user.view.coupon.UsedFragment;
import com.rbu.lamphouse.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 15:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CouponPersenterImpl implements CouponPersenter {
    private Context     mContext;
    private CouponView  mView;
    private CouponModel mModel;

    public CouponPersenterImpl(Context context, CouponView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new CouponModelImpl(context);
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }

    @Override
    public void addCoupon(String input) {
        if (!"".equals(input)) {
            mView.showProgress("");
            OkHttpUtils
                    .postString()
                    .url(Api.couponAdd)
                    .mediaType(MediaType.parse("application/json"))
                    .addHeader("token", mModel.getToken())
                    .content(new Gson().toJson(new AddCouponEntity("1", "10", "20180625")))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.e(Api.couponAdd + "\n" + e.getMessage());
                            mView.dismissProgress("");
                            mView.showToast(mContext.getString(R.string.checknet));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.e("URL" + Api.couponAdd + "\n" + response);
                            mView.dismissProgress("");
                            Gson gson = new Gson();
                            try {
                                if (mModel.checkMsg(gson, response)) {
                                    mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());

                                } else {
                                    mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());
                                }
                            } catch (Exception e) {
                                mView.showToast(mContext.getString(R.string.jsonerror));
                            }
                        }
                    });
        }
    }

    @Override
    public void showUnusedFragment() {
        mView.setUnusedTab();
        mView.switchFragment(new UnusedFragment());
    }

    @Override
    public void showUsedFragment() {
        mView.setUsedTab();
        mView.switchFragment(new UsedFragment());
    }

    @Override
    public void showOverdueFragment() {
        mView.setOverdueTab();
        mView.switchFragment(new OverdueFragment());
    }

    @Override
    public void initData() {
        //请求接口，得到优惠券总数
        String token = mModel.getToken();
        OkHttpUtils
                .get()
                .url(Api.couponTotal)
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.couponTotal + "\n" + e.getMessage());
                        mView.showToast(mContext.getString(R.string.checknet));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.couponTotal + "\n" + response);
                        Gson gson = new Gson();
                        try {
                            if (mModel.checkMsg(gson, response)) {
                                //访问成功，更新页面
                                UserNetEntity data = gson.fromJson(response, UserNetEntity.class);
                                mView.setOverdueValue(data.getData().getOverDue() + "");
                                mView.setUnusedValue(data.getData().getUnUse() + "");
                                mView.setUsedValue(data.getData().getUsed() + "");
                            } else {
                                //访问失败，提示用户
                                mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            //                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });
    }
}
