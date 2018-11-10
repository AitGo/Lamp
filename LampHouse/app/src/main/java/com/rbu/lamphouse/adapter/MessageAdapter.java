package com.rbu.lamphouse.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.diagnose.MessageEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/21 9:51
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MessageAdapter extends BaseQuickAdapter<MessageEntity,BaseViewHolder> {

    private boolean isVisibility = false;


    public MessageAdapter(int layoutResId, @Nullable List<MessageEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        helper.setText(R.id.item_title,item.getTitle());
        helper.setText(R.id.item_time,item.getCreateTime());
//        helper.addOnClickListener(R.id.item_rl);
        helper.setGone(R.id.item_check,isVisibility);
        if(item.isCheck()) {
            helper.setBackgroundRes(R.id.item_check,R.mipmap.dagou);
        }else {
            helper.setBackgroundRes(R.id.item_check,R.mipmap.dagou_1);
        }

    }

    public void setEditVisibility(boolean isVisibility) {
        this.isVisibility = isVisibility;
    }
}
