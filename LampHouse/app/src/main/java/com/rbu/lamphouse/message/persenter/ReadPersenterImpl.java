package com.rbu.lamphouse.message.persenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.MessageAdapter;
import com.rbu.lamphouse.base.Api;
import com.rbu.lamphouse.diagnose.DeleteMsgEntity;
import com.rbu.lamphouse.diagnose.MessageEntity;
import com.rbu.lamphouse.diagnose.MsgNetEntity;
import com.rbu.lamphouse.diagnose.PageDataEntity;
import com.rbu.lamphouse.diagnose.PageEntity;
import com.rbu.lamphouse.diagnose.UserNetEntity;
import com.rbu.lamphouse.message.model.ReadModel;
import com.rbu.lamphouse.message.model.ReadModelImpl;
import com.rbu.lamphouse.message.view.MessageInfoActivity;
import com.rbu.lamphouse.message.view.ReadView;
import com.rbu.lamphouse.utils.LogUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ReadPersenterImpl implements ReadPersenter {
    private Context   mContext;
    private ReadView  mView;
    private ReadModel mModel;

    public ReadPersenterImpl(Context context, ReadView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new ReadModelImpl(context);
    }

    @Override
    public void initData(int pageNum, final int pageSize, int state, final boolean isClear) {
        String token = mModel.getToken();
        if (!"".equals(token)) {
            PageEntity pageEntity = new PageEntity(pageNum, pageSize, state);
            PageDataEntity dataEntity = new PageDataEntity();
            dataEntity.setData(pageEntity);
            OkHttpUtils
                    .postString()
                    .url(Api.messageList)
                    .mediaType(MediaType.parse("application/json"))
                    .addHeader("token", token)
                    .content(new Gson().toJson(dataEntity))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.e("URL" + Api.messageList + "\n" + e.getMessage());
                            mView.showToast(mContext.getString(R.string.checknet));
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.e("URL" + Api.messageList + "\n" + response);
                            Gson gson = new Gson();
                            try {
                                if (mModel.checkMsg(gson, response)) {
                                    MsgNetEntity data = gson.fromJson(response, MsgNetEntity.class);
                                    mView.notifyMsgList(data.getData(),isClear);
                                    mView.addPageNum();
                                    if (!data.getPage().isHasNextPage()) {
                                        mView.getrefreshLayout().finishLoadMoreWithNoMoreData();
                                    }
                                } else {
                                    //访问失败，提示用户
                                    //                                mView.showToast(gson.fromJson(response,UserNetEntity.class).getMessage());
                                }
                            } catch (Exception e) {
                                mView.showToast(mContext.getString(R.string.jsonerror));
                            }
                        }
                    });
        }
    }

    @Override
    public void initData(int pageNum, final int pageSize, int state, final RefreshLayout refreshlayout, final int type) {
        PageEntity pageEntity = new PageEntity(pageNum, pageSize, state);
        String token = mModel.getToken();
        PageDataEntity dataEntity = new PageDataEntity();
        dataEntity.setData(pageEntity);
        OkHttpUtils
                .postString()
                .url(Api.messageList)
                .mediaType(MediaType.parse("application/json"))
                .addHeader("token", token)
                .content(new Gson().toJson(dataEntity))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("URL" + Api.messageList + "\n" + e.getMessage());
                        mView.showToast(mContext.getString(R.string.checknet));
                        if (type == 0) {
                            refreshlayout.finishRefresh(false);
                        } else if (type == 1) {
                            refreshlayout.finishLoadMore(false);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("URL" + Api.messageList + "\n" + response);
                        Gson gson = new Gson();
                        try {
                            if (mModel.checkMsg(gson, response)) {
                                MsgNetEntity data = gson.fromJson(response, MsgNetEntity.class);
                                if (type == 0) {
                                    mView.notifyMsgList(data.getData(),true);
                                    mView.addPageNum();
                                    refreshlayout.finishRefresh(true);
                                    refreshlayout.setNoMoreData(data.getPage().isHasNextPage());
                                    if (!data.getPage().isHasNextPage()) {
                                        refreshlayout.finishLoadMoreWithNoMoreData();
                                    }
                                } else if (type == 1) {
                                    mView.notifyMsgList(data.getData(),false);
                                    mView.addPageNum();
                                    refreshlayout.finishLoadMore(true);
                                    if (!data.getPage().isHasNextPage()) {
                                        refreshlayout.finishLoadMoreWithNoMoreData();
                                    }
                                    refreshlayout.setNoMoreData(!data.getPage().isHasNextPage());
                                }

                            } else {
                                //访问失败，提示用户
                                mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());
                                if (type == 0) {
                                    refreshlayout.finishRefresh(false);
                                } else if (type == 1) {
                                    refreshlayout.finishLoadMore(false);
                                }
                            }
                        } catch (Exception e) {
                            mView.showToast(mContext.getString(R.string.jsonerror));
                            if (type == 0) {
                                refreshlayout.finishRefresh(false);
                            } else if (type == 1) {
                                refreshlayout.finishLoadMore(false);
                            }
                        }
                    }
                });
    }

    @Override
    public void startMessageInfo(boolean isEdit, MessageEntity entity, MessageAdapter adapter) {
        if (!isEdit) {
            Intent intent = new Intent(mContext, new MessageInfoActivity().getClass());
            intent.putExtra("id", entity.getId());
            mContext.startActivity(intent);
        } else {
            entity.setCheck(!entity.isCheck());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteMsg(final List<MessageEntity> msgList, final MessageAdapter adapter) {
        final List<Integer> deletes = new ArrayList<>();
        for (MessageEntity entity : msgList) {
            if (entity.isCheck()) {
                deletes.add(entity.getId());
            }
        }
        DeleteMsgEntity entity = new DeleteMsgEntity();
        entity.setIds(deletes);
        mView.showProgress("");
        String token = mModel.getToken();
        OkHttpUtils
                .postString()
                .url(Api.messageDelete)
                .mediaType(MediaType.parse("application/json"))
                .addHeader("token", token)
                .content(new Gson().toJson(entity))
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
                            if (mModel.checkStateMsg(gson, response)) {

                                Iterator<MessageEntity> it = msgList.iterator();
                                while (it.hasNext()) {
                                    MessageEntity x = it.next();
                                    if (x.isCheck()) {
                                        it.remove();
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                mView.showToast(gson.fromJson(response, UserNetEntity.class).getMessage());
                            }
                        } catch (Exception e) {
                            LogUtils.e(e.getMessage());
                            mView.showToast(mContext.getString(R.string.jsonerror));
                        }
                    }
                });

    }
}
