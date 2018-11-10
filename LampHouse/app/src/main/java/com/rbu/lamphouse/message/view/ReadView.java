package com.rbu.lamphouse.message.view;

import com.rbu.lamphouse.diagnose.MessageEntity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface ReadView {

    void showToast(String msg);

    void notifyMsgList(List<MessageEntity> data,boolean isClear);

    void addPageNum();

    void clearPageNum();

    void showProgress(String msg);

    void dismissProgress(String msg);

    RefreshLayout getrefreshLayout();
}
