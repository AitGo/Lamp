package com.rbu.lamphouse.setting.model;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;

import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.utils.SPUtils;

import java.util.Locale;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 17:18
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LanguageModelImpl implements LanguageModel {

    private Context mContext;

    public LanguageModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public String getLanguageConfig() {
        return (String) SPUtils.getParam(mContext, Constants.Language,"");
    }

    @Override
    public void saveLanguageConfig(String language) {
        SPUtils.setParam(mContext,Constants.Language,language);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void switchLanguage(String language) {

        Configuration config = mContext.getResources().getConfiguration();// 获得设置对象
        Resources resources = mContext.getResources();// 获得res资源对象
        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
        if(language.equals("en")) {
            config.locale = Locale.ENGLISH;
        } else if(language.equals("es")) {
            config.locale = new Locale("es");
        } else if(language.equals("fr")) {
            config.locale = Locale.FRENCH;
        } else if(language.equals("de")) {
            config.locale = Locale.GERMAN;
        } else if(language.equals("it")) {
            config.locale = Locale.ITALIAN;
        } else if(language.equals("ru")) {
            config.locale = new Locale("ru");
        } else if(language.equals("ja")) {
            config.locale = Locale.JAPANESE;
        }

        resources.updateConfiguration(config, dm);
    }
}
