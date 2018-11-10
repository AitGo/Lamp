package com.rbu.lamphouse.user.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.HelpCenterPersenter;
import com.rbu.lamphouse.user.persenter.HelpCenterPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @创建者 liuyang
 * @创建时间 2018/6/14 10:41
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HelpCenterActivity extends BaseActivity implements HelpCenterView, View.OnClickListener {
    private HelpCenterPersenter mPersenter;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private String helpHtmlUrl = "file:///android_asset/help.html";
    private LinearLayout back;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_helpcenter;
    }

    @Override
    protected void initView() {
        mPersenter = new HelpCenterPersenterImpl(this,this);

        mWebView = findViewById(R.id.webview);
        back = findViewById(R.id.help_back);
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mWebView.loadUrl(helpHtmlUrl);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mWebSettings.setMixedContentMode(mWebSettings
                    .MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        mWebView.setWebViewClient(new MyWebClient());
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_back:
                mPersenter.finishActivity();
                break;
        }
    }

    @Override
    public void webViewLoadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    private class MyWebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            mPersenter.reSizeImg();
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mPersenter.shouldOverrideUrlLoading(url);
            return true;
        }
    }

}
