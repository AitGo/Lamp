package com.rbu.lamphouse.message.view;

import android.graphics.Bitmap;

import com.rbu.lamphouse.diagnose.MessageEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 9:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public interface MessageInfoView {

    void finishActivity();

    void showToast(String msg);

    void showProgress(String msg);

    void dismissProgress(String msg);

    void setTitle(String title);

    void setWebView(String text);

    void loadUrl(String url);
}
