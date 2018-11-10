package com.rbu.lamphouse.user.persenter.coupon;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.CouponNetEntity;
import com.rbu.lamphouse.diagnose.PageEntity;
import com.rbu.lamphouse.user.model.coupon.OverdueModel;
import com.rbu.lamphouse.user.model.coupon.OverdueModelImpl;
import com.rbu.lamphouse.user.model.coupon.UsedModel;
import com.rbu.lamphouse.user.model.coupon.UsedModelImpl;
import com.rbu.lamphouse.user.view.coupon.OverdueView;
import com.rbu.lamphouse.user.view.coupon.UsedView;
import com.rbu.lamphouse.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

public class OverduePersenterImpl implements OverduePersenter {

    private Context      mContext;
    private OverdueView  mView;
    private OverdueModel mModel;

    public OverduePersenterImpl(Context context,OverdueView view) {
        this.mContext = context;
        this.mView = view;
        this.mModel = new OverdueModelImpl(context);
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
