package com.rbu.lamphouse.message.persenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.message.model.MessageModel;
import com.rbu.lamphouse.message.model.MessageModelImpl;
import com.rbu.lamphouse.message.view.MessageView;
import com.rbu.lamphouse.message.view.ReadFragment;
import com.rbu.lamphouse.message.view.UnReadFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MessagePersenterImpl implements MessagePersenter{
    private Context mContext;
    private MessageView mView;
    private MessageModel mModel;


    public MessagePersenterImpl(Context context,MessageView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new MessageModelImpl(context);

    }

    @Override
    public void showUnReadFragment(Fragment fragment) {
//        mView.switchFragment(mUnReadFragment);
        mView.hideFragment();
        mView.showFragment(fragment);
        mView.chooseUnReadTab();
//        EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_SHOWLIST));
    }

    @Override
    public void showReadFragment(Fragment fragment) {
//        mView.switchFragment(mReadFragment);
        mView.hideFragment();
        mView.showFragment(fragment);
        mView.chooseReadTab();
//        EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_SHOWLIST));
    }

    @Override
    public void edit() {
        mView.setEditVisibility(View.GONE);
        mView.setAllVisibility(View.VISIBLE);
        mView.setDoneVisibility(View.VISIBLE);
        mView.setDeleteVisibility(View.VISIBLE);
        if(mView.getUnReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_UNREAD_EDIT));
        }
        if(mView.getReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_READ_EDIT));
        }

    }

    @Override
    public void all() {
        if(mView.getUnReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_UNREAD_ALL));
        }
        if(mView.getReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_READ_ALL));
        }
    }

    @Override
    public void done() {
        mView.setEditVisibility(View.VISIBLE);
        mView.setAllVisibility(View.GONE);
        mView.setDoneVisibility(View.GONE);
        mView.setDeleteVisibility(View.GONE);
        if(mView.getUnReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_UNREAD_DONE));
        }
        if(mView.getReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_READ_DONE));
        }
    }

    @Override
    public void delete() {
        if(mView.getUnReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_UNREAD_DELETE));
        }
        if(mView.getReadFragment().isVisible()) {
            EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_READ_DELETE));
        }
    }
}
