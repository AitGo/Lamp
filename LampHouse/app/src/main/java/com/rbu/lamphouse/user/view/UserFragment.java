package com.rbu.lamphouse.user.view;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.setting.view.SettingActivity;
import com.rbu.lamphouse.user.persenter.UserPersenter;
import com.rbu.lamphouse.user.persenter.UserPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.SPUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.AppTextView;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 10:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class UserFragment extends BaseFragment implements UserView, View.OnClickListener {

    private UserPersenter mPersenter;
    private RelativeLayout cash,setting,help,about;
    private LinearLayout login,regist,logout;
    private AppTextView        userName;
    private LampProgressDialog mProgressDialog;

    private View inflate;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View inflate) {
        this.inflate = inflate;
        mPersenter = new UserPersenterImpl(getContext(),this);
        regist = inflate.findViewById(R.id.user_register);
        login = inflate.findViewById(R.id.user_login);
        logout = inflate.findViewById(R.id.user_logout);
        cash = inflate.findViewById(R.id.user_cash);
        setting = inflate.findViewById(R.id.user_setting);
        help = inflate.findViewById(R.id.user_help);
        about = inflate.findViewById(R.id.user_about);
        userName = inflate.findViewById(R.id.user_name);

        regist.setOnClickListener(this);
        login.setOnClickListener(this);
        logout.setOnClickListener(this);
        cash.setOnClickListener(this);
        setting.setOnClickListener(this);
        help.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mPersenter.initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
        if(!"".equals(SPUtils.getParam(getContext(),Constants.token,""))){
            userName.setText((String) SPUtils.getParam(getContext(),Constants.email,""));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UIChangedEvent event) {
        if(event.getMsg() == UIChangedEvent.MSG_LOGIN) {
            mPersenter.login();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_register:
                mPersenter.startActivity(new RegisterActivity());
                break;
            case R.id.user_login:
                mPersenter.startActivity(new LoginActivity());
                break;

            case R.id.user_logout:
                mPersenter.logout();
                break;

            case R.id.user_cash:
                mPersenter.startCoupon();
//                mPersenter.startActivity(new CouponActivity());
                break;

            case R.id.user_setting:
                mPersenter.startActivity(new SettingActivity());
                break;

            case R.id.user_help:
                mPersenter.startActivity(new HelpCenterActivity());
                break;

            case R.id.user_about:
                mPersenter.startActivity(new AboutUsActivity());
                break;
        }
    }

    @Override
    public void setUserName(String name) {
        userName.setText(name);
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public void SetLoginVisibility(int visibility) {
        login.setVisibility(visibility);
    }

    @Override
    public void SetRegistVisibility(int visibility) {
        regist.setVisibility(visibility);
    }

    @Override
    public void setLogouttVisibility(int visibility) {
        logout.setVisibility(visibility);
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new LampProgressDialog(getContext(),msg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress(String msg) {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
