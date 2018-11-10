package com.rbu.lamphouse.message.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.message.persenter.MessageInfoPersenter;
import com.rbu.lamphouse.message.persenter.MessageInfoPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppTextView;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/22 14:59
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MessageInfoActivity extends BaseActivity implements MessageInfoView, View.OnClickListener {
    private MessageInfoPersenter mPersenter;
    private LinearLayout back;
    private AppTextView title;

    private LampProgressDialog mProgressDialog;
    private WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messageinfo;
    }

    @Override
    protected void initView() {
        mPersenter = new MessageInfoPersenterImpl(this,this);
        back = findViewById(R.id.info_back);
        title = findViewById(R.id.info_title);

        webview = findViewById(R.id.info_web);

        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        mPersenter.initData(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_back:
                mPersenter.finishActivity();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new LampProgressDialog(this,msg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress(String msg) {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setWebView(String content) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setDefaultTextEncodingName("UTF-8") ;
        webview.getSettings().setBlockNetworkImage(false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            webview.getSettings().setMixedContentMode(webview.getSettings()
                    .MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        webview.loadDataWithBaseURL(null, getNewContent(content), "text/html", "UTF-8", null);
    }

    @Override
    public void loadUrl(String url) {

    }

    private String getNewContent(String htmltext){

        Document doc=Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }

        return doc.toString();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
