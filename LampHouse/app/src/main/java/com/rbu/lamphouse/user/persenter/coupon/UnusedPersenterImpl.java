package com.rbu.lamphouse.user.persenter.coupon;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.CouponNetEntity;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.diagnose.PageEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.user.model.coupon.UnusedModel;
import com.rbu.lamphouse.user.model.coupon.UnusedModelImpl;
import com.rbu.lamphouse.user.view.coupon.UnusedView;
import com.rbu.lamphouse.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 17:37
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UnusedPersenterImpl implements UnusedPersenter {
    private Context mContext;
    private UnusedView mView;
    private UnusedModel mModel;


    public UnusedPersenterImpl(Context context,UnusedView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new UnusedModelImpl(context);
    }

    @Override
    public void initData(final int pageNum, int pageSize, int state, final boolean isClear) {
        mView.showProgress("");
        OkHttpUtils
                .postString()
                .url(Api.couponList)
                .mediaType(MediaType.parse("application/json"))
                .addHeader("token",mModel.getToken())
                .content(new Gson().toJson(new PageEntity(pageNum,pageSize,state)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.couponList + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                        mView.getAdapter().loadMoreFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.couponList + "\n" + response);
                        mView.dismissProgress("");
                        Gson gson = new Gson();
                        try {
                            if (mModel.checkMsg(gson, response)) {
                                CouponNetEntity data = gson.fromJson(response,CouponNetEntity.class);
                                mView.notifyMsgList(data.getData(),isClear);
                                mView.addPageNum();
                                if(data.getPage().getPages() > pageNum) {
                                    mView.getAdapter().loadMoreComplete();
                                }else {
                                    mView.getAdapter().loadMoreEnd();
                                }
                            } else {
                                mView.getAdapter().loadMoreFail();
                                mView.showToast(gson.fromJson(response,CouponNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            mView.getAdapter().loadMoreFail();
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });
    }
}
