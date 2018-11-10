package com.rbu.lamphouse.setting.persenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.setting.model.SettingModel;
import com.rbu.lamphouse.setting.model.SettingModelImpl;
import com.rbu.lamphouse.setting.view.LanguageActivity;
import com.rbu.lamphouse.setting.view.SettingView;
import com.rbu.lamphouse.utils.DataUtils;
import com.rbu.lamphouse.utils.LogUtils;


/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 15:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class SettingPersenterImpl implements SettingPersenter{

    private final String TAG = this.getClass().getSimpleName();
    private SettingView  view;
    private SettingModel model;
    private Context      context;

    public SettingPersenterImpl(Context context, SettingView view) {
        this.view = view;
        this.context = context;
        model = new SettingModelImpl(context);
    }


    @Override
    public void startActivity(Activity activity) {
        Intent intent = new Intent(context,activity.getClass());
        context.startActivity(intent);
    }

    @Override
    public void timerOffClick() {
        view.showTimeOffPicker();

    }

    @Override
    public void timerOnClick() {
        view.showTimeOnPicker();

    }

    @Override
    public void setTimerOffText(String time) {
        view.setTimerOffText(time);
        model.saveOffTime(time);
    }

    @Override
    public void setTimerOnText(String time) {
        view.setTimerOnText(time);
        model.saveOnTime(time);
    }

    @Override
    public void initTimeTextData() {
        String offTime = model.getOffTime();
        if(!"".equals(offTime)) {
            view.setTimerOffText(offTime);
        }

        String onTime = model.getOnTime();
        if(!"".equals(onTime)) {
            view.setTimerOnText(onTime);
        }
    }

    @Override
    public void initTimePicker() {
        view.initTimePicker();
    }

    @Override
    public void initSwitch() {
        view.setAutoConnSwitch(model.getAutoConn());
        view.setAutoDisConnSwitch(model.getAutoDisconn());
        view.setTimerOffSwitch(model.getTimerOff());
        view.setTimerOnSwitch(model.getTimerOn());
    }

    @Override
    public void finishActivity() {
        view.finishActivity();
    }

    @Override
    public void autoConnSwitch(boolean isChecked) {
        //保存到配置文件
        model.saveAutoConn(isChecked);
        model.setAutoConn(isChecked);
    }

    @Override
    public void autoDisConnSwitch(boolean isChecked) {
        model.saveAutoDisconn(isChecked);
        model.setAutoDisconn(isChecked);
    }

    @Override
    public void timerOffSwitch(boolean isChecked) {
        if(isChecked && "".equals(view.getTimerOffText())) {
            view.showToast(context.getString(R.string.choosetime));
            view.setTimerOffSwitch(false);
        }else {
            if(isChecked) {
                view.setTimerOnSwitch(false);
                model.saveTimerOn(false);
                model.setTimerOn(false,view.getTimerOnText());
            }
            model.saveTimerOff(isChecked);
            model.setTimerOff(isChecked,view.getTimerOffText());
        }

    }

    @Override
    public void timerOnSwitch(boolean isChecked) {
        if(isChecked && "".equals(view.getTimerOnText())) {
            view.showToast(context.getString(R.string.choosetime));
            view.setTimerOnSwitch(false);
        }else {
            if(isChecked) {
                view.setTimerOffSwitch(false);
                model.saveTimerOff(false);
                model.setTimerOff(false,view.getTimerOffText());
            }
            model.saveTimerOn(isChecked);
            model.setTimerOn(isChecked,view.getTimerOnText());
        }
    }

}
