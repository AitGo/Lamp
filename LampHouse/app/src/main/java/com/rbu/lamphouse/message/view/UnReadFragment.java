package com.rbu.lamphouse.message.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.MessageAdapter;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.diagnose.MessageEntity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.message.persenter.UnReadPersenter;
import com.rbu.lamphouse.message.persenter.UnReadPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.LampProgressDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UnReadFragment extends BaseFragment implements UnReadView {
    private UnReadPersenter mPersenter;
    private RecyclerView    recyclerView;
    private RefreshLayout   refreshLayout;
    private MessageAdapter  mAdapter;
    private View            inflate;
    private List<MessageEntity> msgList  = new ArrayList<>();
    private boolean             isEdit   = false;
    private int                 pageNum  = 1;
    private int                 pageSize = 8;
    private int                 state    = 0;
    private LampProgressDialog mProgressDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_unread;
    }

    @Override
    protected void initView(View inflate) {
        this.inflate = inflate;
        mPersenter = new UnReadPersenterImpl(getContext(), this);
        recyclerView = inflate.findViewById(R.id.unread_recyceler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout = (RefreshLayout) inflate.findViewById(R.id.unread_refreshLayout);

    }

    @Override
    protected void initData() {
        //        msgList.clear();
        //        pageNum = 1;
        //        mPersenter.initData(pageNum,pageSize,state);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setNoMoreData(false);
        mAdapter = new MessageAdapter(R.layout.item_message, msgList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPersenter.startMessageInfo(isEdit, msgList.get(position), mAdapter);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                pageNum = 1;
                msgList.clear();
                mPersenter.initData(pageNum, pageSize, state, refreshlayout, 0);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                LogUtils.e("pageNum:" + pageNum);
                mPersenter.initData(pageNum, pageSize, state, refreshlayout, 1);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UIChangedEvent event) {
        if (event.getMsg() == UIChangedEvent.MSG_UNREAD_EDIT) {
            mAdapter.setEditVisibility(true);
            mAdapter.notifyDataSetChanged();
            isEdit = true;
        } else if (event.getMsg() == UIChangedEvent.MSG_UNREAD_DONE) {
            mAdapter.setEditVisibility(false);
            mAdapter.notifyDataSetChanged();
            isEdit = false;
            for (MessageEntity entity : msgList) {
                entity.setCheck(false);
            }
        } else if (event.getMsg() == UIChangedEvent.MSG_UNREAD_DELETE) {
            mPersenter.deleteMsg(msgList, mAdapter);
        } else if (event.getMsg() == UIChangedEvent.MSG_UNREAD_ALL) {
            //            mPersenter
            boolean isCkeck = true;
            for (MessageEntity entity : msgList) {
                if (!entity.isCheck()) {
                    isCkeck = true;
                    break;
                } else {
                    isCkeck = false;
                }

            }
            for (MessageEntity entity : msgList) {
                entity.setCheck(isCkeck);
            }
            mAdapter.notifyDataSetChanged();
        } else if (event.getMsg() == UIChangedEvent.MSG_LOGIN) {
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setNoMoreData(false);
            msgList.clear();
            pageNum = 1;
            mPersenter.initData(pageNum, pageSize, state,true);
        } else if (event.getMsg() == UIChangedEvent.MSG_LOGOUT) {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setNoMoreData(false);
            msgList.clear();
            pageNum = 1;
            mAdapter.notifyDataSetChanged();
        } else if (event.getMsg() == UIChangedEvent.MSG_UNREAD_INITDATA) {
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setNoMoreData(false);
            msgList.clear();
            pageNum = 1;
            mPersenter.initData(pageNum, pageSize, state,true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //切换到message页面可见时加载
            msgList.clear();
            pageNum = 1;
            mPersenter.initData(pageNum, pageSize, state,true);
            refreshLayout.setNoMoreData(false);
        }
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void notifyMsgList(List<MessageEntity> data,boolean isClear) {
        if(isClear) {
            msgList.clear();
        }
        msgList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addPageNum() {
        pageNum++;
    }

    @Override
    public void clearPageNum() {
        pageNum = 1;
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new LampProgressDialog(getContext(), msg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress(String msg) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public RefreshLayout getrefreshLayout() {
        return refreshLayout;
    }


}
