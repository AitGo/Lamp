package com.rbu.lamphouse.main.model;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.utils.FileUtils;
import com.rbu.lamphouse.utils.SPUtils;

import java.io.File;
import java.util.Locale;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 15:25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MainModelImpl implements MainModel {

    private Context mContext;
    public MainModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void copyColorConfig(String fromFile,String toFile) {
        FileUtils.copy(fromFile,toFile);
    }

    @Override
    public void newFilePath(String filePath) {
        File f = new File(filePath);
        f.mkdirs();
    }

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

    @Override
    public String getLanguage() {
        return (String) SPUtils.getParam(mContext, Constants.Language,"");

    }
}
