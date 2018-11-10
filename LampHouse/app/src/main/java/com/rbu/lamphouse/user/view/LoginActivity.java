package com.rbu.lamphouse.user.view;

import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.user.persenter.LoginPersenter;
import com.rbu.lamphouse.user.persenter.LoginPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppButton;
import com.rbu.lamphouse.widget.AppEditText;
import com.rbu.lamphouse.widget.AppTextView;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/12 16:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {
    private LoginPersenter mPersenter;
    private LinearLayout back;
    private LinearLayout regist;
    private AppButton btn_login;
    private AppTextView forgetpass;
    private AppEditText email,password;
    private LampProgressDialog mProgressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mPersenter = new LoginPersenterImpl(this,this);
        back = findViewById(R.id.login_back);
        regist = findViewById(R.id.login_regist);
        btn_login = findViewById(R.id.login_btn_login);
        forgetpass = findViewById(R.id.login_forgetpass);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        regist.setOnClickListener(this);
        back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                mPersenter.finishActivity();
                break;

            case R.id.login_regist:
                mPersenter.startActivity(new RegisterActivity());
                mPersenter.finishActivity();
                break;

            case R.id.login_btn_login:
                mPersenter.login();
                break;

            case R.id.login_forgetpass:
                mPersenter.startActivity(new ForgetPasswordActivity());
                break;
        }
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
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
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
