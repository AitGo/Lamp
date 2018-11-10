package com.rbu.lamphouse.user.view;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.RegisterPersenter;
import com.rbu.lamphouse.user.persenter.RegisterPersenterImpl;
import com.rbu.lamphouse.utils.CountTimer;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppButton;
import com.rbu.lamphouse.widget.AppEditText;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {
    private RegisterPersenter mPersenter;
    private LinearLayout back;
    private LinearLayout login;
    private AppEditText email,password,tkjn;
    private AppButton btn_tkjn,btn_regist;
    private LampProgressDialog mProgressDialog;

    private CountTimer myCountDownTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mPersenter = new RegisterPersenterImpl(this,this);
        back = findViewById(R.id.regist_back);
        login = findViewById(R.id.regist_login);
        email = findViewById(R.id.regist_email);
        password = findViewById(R.id.regist_password);
        tkjn = findViewById(R.id.regist_code);
        btn_tkjn = findViewById(R.id.regist_btn_tkjn);
        btn_regist = findViewById(R.id.regist_btn_regist);

        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        back.setOnClickListener(this);
        login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
        btn_tkjn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        myCountDownTimer = new CountTimer(this,btn_tkjn,60000,1000);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_back:
                mPersenter.finishActivity();
                break;

            case R.id.regist_login:
                mPersenter.startActivity(new LoginActivity());
                mPersenter.finishActivity();
                break;

            case R.id.regist_btn_tkjn:
                mPersenter.getTkjn();
                break;

            case R.id.regist_btn_regist:
                mPersenter.regist();
                break;
        }

    }


    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setEmail(String text) {
        email.setText(text);
    }

    @Override
    public void setPassword(String text) {
        password.setText(text);
    }

    @Override
    public void setTkjn(String text) {
        tkjn.setText(text);
    }

    @Override
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public String getTkjn() {
        return tkjn.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void startTimer() {
        myCountDownTimer.start();
    }

    @Override
    public void cancelTimer() {
        myCountDownTimer.cancel();
        myCountDownTimer.onFinish();
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
}
