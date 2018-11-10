package com.rbu.lamphouse.user.view.coupon;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.CouponAdapter;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.diagnose.CouponEntity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.coupon.UsedPersenter;
import com.rbu.lamphouse.user.persenter.coupon.UsedPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/19 16:40
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class UsedFragment extends BaseFragment implements UsedView {
    private UsedPersenter mPersenter;
    private View          inflate;
    private RecyclerView  mRecyclerView;
    private CouponAdapter mAdapter;
    private int                pageSize = 8;
    private int                state    = 1;
    private int                pageNum  = 1;
    private List<CouponEntity> mList    = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_used;
    }

    @Override
    protected void initView(View inflate) {
        mPersenter = new UsedPersenterImpl(getContext(), this);
        this.inflate = inflate;
        mRecyclerView = inflate.findViewById(R.id.used_recyceler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        mList.clear();
        pageNum = 1;
        mPersenter.initData(pageNum, pageSize, state,true);

        mAdapter = new CouponAdapter(R.layout.item_coupon_unread, mList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setCopyBtnVisibility(false);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPersenter.initData(pageNum, pageSize, state,false);
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(mList.get(position).getCouponCode());
                Toast.makeText(getContext(), R.string.copy_success, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
    }

    @Override
    public void notifyMsgList(List<CouponEntity> data,boolean isClear) {
        if(isClear) {
            mList.clear();
        }
        mList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void dismissProgress(String msg) {

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
    public CouponAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
