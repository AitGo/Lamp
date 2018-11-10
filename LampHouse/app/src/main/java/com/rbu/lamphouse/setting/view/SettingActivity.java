package com.rbu.lamphouse.setting.view;

import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.setting.persenter.SettingPersenter;
import com.rbu.lamphouse.setting.persenter.SettingPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 15:03
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener,SettingView, CompoundButton.OnCheckedChangeListener {

    private SettingPersenter persenter;

    private RelativeLayout profiles,timeroff,timeron,language;
    private Switch s_timeroff,s_timeron,s_atuoconn,s_autodisconn;
    private TextView tv_timeroff,tv_timeron;
//    private CustomDatePicker customOff, customOn;
    private TimePickerView pvTime_off,pvTime_on;
    private LinearLayout setting_back;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        persenter = new SettingPersenterImpl(this, this);

        setting_back = findViewById(R.id.setting_ll_back);
        profiles = findViewById(R.id.setting_rl_profiles);
        timeroff = findViewById(R.id.setting_rl_timeroff);
        timeron = findViewById(R.id.setting_rl_timeron);
        language = findViewById(R.id.setting_rl_language);
        s_timeroff = findViewById(R.id.setting_switch_timeroff);
        s_timeron = findViewById(R.id.setting_switch_timeron);
        s_atuoconn = findViewById(R.id.setting_switch_autoconn);
        s_autodisconn = findViewById(R.id.setting_switch_autodisconn);
        tv_timeroff = findViewById(R.id.setting_tv_timeroff);
        tv_timeron = findViewById(R.id.setting_tv_timeron);

        setting_back.setOnClickListener(this);
        profiles.setOnClickListener(this);
        timeroff.setOnClickListener(this);
        timeron.setOnClickListener(this);
        language.setOnClickListener(this);

        s_timeroff.setOnCheckedChangeListener(this);
        s_timeron.setOnCheckedChangeListener(this);
        s_atuoconn.setOnCheckedChangeListener(this);
        s_autodisconn.setOnCheckedChangeListener(this);



    }

    @Override
    protected void initData() {
        persenter.initTimeTextData();
        persenter.initTimePicker();
        persenter.initSwitch();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void initTimePicker() {
        pvTime_off = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                persenter.setTimerOffText(getTime(date));
                persenter.timerOffSwitch(s_timeroff.isChecked());
            }
        })
                .setType(new boolean[]{false, false, false, true, true, true})
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime_off.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime_off.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }

        pvTime_on = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                persenter.setTimerOnText(getTime(date));
                persenter.timerOnSwitch(s_timeron.isChecked());
            }
        })
                .setType(new boolean[]{false, false, false, true, true, true})
                .isDialog(true)
                .build();

        Dialog mDialogOn = pvTime_on.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime_on.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialogOn.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_ll_back:
                persenter.finishActivity();
                break;
            case R.id.setting_rl_profiles:
                persenter.startActivity(new ProfilesActivity());
                break;

            case R.id.setting_rl_timeroff:
                LogUtils.d("off");
                persenter.timerOffClick();
                break;

            case R.id.setting_rl_timeron:
                LogUtils.d("on");
                persenter.timerOnClick();
                break;

            case R.id.setting_rl_language:
                persenter.startActivity(new LanguageActivity());
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.setting_switch_timeroff:
                persenter.timerOffSwitch(isChecked);

                break;

            case R.id.setting_switch_timeron:
                persenter.timerOnSwitch(isChecked);

                break;

            case R.id.setting_switch_autoconn:
                persenter.autoConnSwitch(isChecked);

                break;

            case R.id.setting_switch_autodisconn:
                persenter.autoDisConnSwitch(isChecked);

                break;
        }
    }

    @Override
    public void setTimerOffText(String time) {
        tv_timeroff.setText(time);
    }

    @Override
    public void setTimerOnText(String time) {
        tv_timeron.setText(time);
    }

    @Override
    public String getTimerOffText() {
        return tv_timeroff.getText().toString();
    }

    @Override
    public String getTimerOnText() {
        return tv_timeron.getText().toString();
    }


    @Override
    public boolean getTimerOffChecked() {
        return s_timeroff.isChecked();
    }

    @Override
    public boolean getTimerOnChecked() {
        return s_timeron.isChecked();
    }

    @Override
    public boolean getAutoConnChecked() {
        return s_atuoconn.isChecked();
    }

    @Override
    public boolean getAutoDisConnChecked() {
        return s_autodisconn.isChecked();
    }

    @Override
    public void showTimeOffPicker() {
//        customOff.show("2018-06-05 23:46");
        pvTime_off.show();
    }

    @Override
    public void showTimeOnPicker() {
//        customOn.show(getTimerOnText());
        pvTime_on.show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setTimerOffSwitch(boolean isClick) {
        s_timeroff.setChecked(isClick);
    }

    @Override
    public void setTimerOnSwitch(boolean isClick) {
        s_timeron.setChecked(isClick);
    }

    @Override
    public void setAutoConnSwitch(boolean isClick) {
        s_atuoconn.setChecked(isClick);
    }

    @Override
    public void setAutoDisConnSwitch(boolean isClick) {
        s_autodisconn.setChecked(isClick);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
