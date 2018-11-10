package com.rbu.lamphouse.message.persenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.DataEntity;
import com.rbu.lamphouse.diagnose.MessageInfoEntity;
import com.rbu.lamphouse.diagnose.MsgInfoNetEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.message.model.MessageInfoModel;
import com.rbu.lamphouse.message.model.MessageInfoModelImpl;
import com.rbu.lamphouse.message.view.MessageInfoView;
import com.rbu.lamphouse.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 9:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MessageInfoPersenterImpl implements MessageInfoPersenter {
    private Context mContext;
    private MessageInfoView mView;
    private MessageInfoModel mModel;

    public MessageInfoPersenterImpl(Context context,MessageInfoView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new MessageInfoModelImpl(context);
    }

    @Override
    public void initData(int id) {
        mView.showProgress("");
        String token = mModel.getToken();
        OkHttpUtils
                .get()
                .url(Api.messageInfo + "/" + id + "/" + 0)
                .addHeader("token",token)
//                .addParams("id",id + "")
//                .addParams("isRead",1 + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.messageInfo + "\n" + e.getMessage());
                        mView.dismissProgress("");
                        mView.showToast(mContext.getString(R.string.checknet));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.messageInfo + "\n" + response);
                        mView.dismissProgress("");
                        Gson gson = new Gson();
                        try {
//                            if(mModel.checkMsg(gson,response)) {
//                                MsgInfoNetEntity data = gson.fromJson(response,MsgInfoNetEntity.class);
//                                mView.setTitle(data.getData().getTitle());
//                                mView.setWebView(data.getData().getDetail());
                            mView.setWebView(response);
//                            mView.loadUrl(mModel.reSizeImgJaveScript());
                            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_UNREAD_INITDATA));
//                            }else {
//                                MsgInfoNetEntity data = gson.fromJson(response,MsgInfoNetEntity.class);
//                                mView.showToast(data.getMessage());
//                            }


                        } catch (Exception e) {
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }


}
