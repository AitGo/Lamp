package com.rbu.lamphouse.message.persenter;

import com.rbu.lamphouse.adapter.MessageAdapter;
import com.rbu.lamphouse.diagnose.MessageEntity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/20 15:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface ReadPersenter {

    void initData(int pageNum,int pageSize, int state,boolean isClear);

    void initData(int pageNum,int pageSize, int state,RefreshLayout refreshlayout,int type);

    void startMessageInfo(boolean isEdit, MessageEntity entity, MessageAdapter adapter);

    void deleteMsg(List<MessageEntity> msgList, MessageAdapter adapter);
}
