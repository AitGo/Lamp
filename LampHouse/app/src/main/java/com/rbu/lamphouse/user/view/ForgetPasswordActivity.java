package com.rbu.lamphouse.user.view;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.ForgetPasswordPersenter;
import com.rbu.lamphouse.user.persenter.ForgetPasswordPersenterImpl;
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
 * @创建时间 2018/6/13 17:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordView, View.OnClickListener {
    private ForgetPasswordPersenter mPersenter;
    private LinearLayout back;
    private AppEditText email,tkjn,password,password_confirm;
    private AppButton btn_confirm,btn_tkjn;
    private CountTimer myCountDownTimer;
    private LampProgressDialog mProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initView() {
        mPersenter = new ForgetPasswordPersenterImpl(this,this);

        back = findViewById(R.id.forget_back);
        email = findViewById(R.id.forget_email);
        tkjn = findViewById(R.id.forget_tkjn);
        password = findViewById(R.id.forget_password);
        password_confirm = findViewById(R.id.forget_password_confirm);
        btn_tkjn = findViewById(R.id.forget_btn_tkjn);
        btn_confirm = findViewById(R.id.forget_btn_confirm);

        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
        password_confirm.setTypeface(Typeface.DEFAULT);
        password_confirm.setTransformationMethod(new PasswordTransformationMethod());

        back.setOnClickListener(this);
        btn_tkjn.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
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
            case R.id.forget_back:
                mPersenter.finishActivity();
                break;

            case R.id.forget_btn_tkjn:
                mPersenter.getTkjn();
                break;

            case R.id.forget_btn_confirm:
                mPersenter.confirm();
                break;
        }
    }

//    //复写倒计时
//    private class MyCountDownTimer extends CountDownTimer {
//
//        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        //计时过程
//        @Override
//        public void onTick(long l) {
//            //防止计时过程中重复点击
//            btn_tkjn.setClickable(false);
//            btn_tkjn.setText(l/1000+"s");
//            btn_tkjn.setBackgroundColor(getResources().getColor(R.color.bg_seek_normal));
//        }
//
//        //计时完毕的方法
//        @Override
//        public void onFinish() {
//            //重新给Button设置文字
//            btn_tkjn.setText(getString(R.string.tkjn));
//            //设置可点击
//            btn_tkjn.setClickable(true);
//            btn_tkjn.setBackgroundColor(getResources().getColor(R.color.bg_blue));
//        }
//    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public void setEmail(String text) {
        email.setText(text);
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void setPassword(String text) {
        password.setText(text);
    }

    @Override
    public String getPasswordConfirm() {
        return password_confirm.getText().toString().trim();
    }

    @Override
    public void setPasswordConfirm(String text) {
        password_confirm.setText(text);
    }

    @Override
    public String getTkjn() {
        return tkjn.getText().toString().trim();
    }

    @Override
    public void setTkjn(String text) {
        tkjn.setText(text);
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
