package com.rbu.lamphouse.main.persenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.device.view.DeviceFragment;
import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.diagnose.Config;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.home.view.HomeFragment;
import com.rbu.lamphouse.main.model.MainModel;
import com.rbu.lamphouse.main.model.MainModelImpl;
import com.rbu.lamphouse.main.view.MainView;
import com.rbu.lamphouse.message.view.MessageFragment;
import com.rbu.lamphouse.user.view.UserFragment;
import com.rbu.lamphouse.utils.ColorXmlUtils;
import com.rbu.lamphouse.utils.ConfigXmlUtils;
import com.rbu.lamphouse.utils.FileUtils;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 15:25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class MainPersenterImpl implements MainPersenter {

    private final String TAG = this.getClass().getSimpleName();
    private MainView  view;
    private MainModel model;
    private Context   context;

    private HomeFragment mHomeFragment;
    private DeviceFragment mDeviceFragment;
    private MessageFragment mMessageFragment;
    private UserFragment mUserFragment;

    private long exitTime = 0;// 退出时间

    public MainPersenterImpl(Context context, MainView view) {
        this.view = view;
        this.context = context;
        model = new MainModelImpl(context);
    }


    @Override
    public void showHomeFragment(Fragment fragment) {
//        if(fragment == null) {
//            fragment = new HomeFragment();
//        }
//        view.switchFragment(fragment);
        view.hideFragment();
        view.showFragment(fragment);
        view.setTabUnCheck();
        view.setHomeTab(true);
    }

    @Override
    public void showDeviceFragment(Fragment fragment) {
//        if(fragment == null) {
//            fragment = new DeviceFragment();
//        }
//        view.switchFragment(fragment);
        view.hideFragment();
        view.showFragment(fragment);
        view.setTabUnCheck();
        view.setDeviceTab(true);
    }

    @Override
    public void showMessageFragment(Fragment fragment) {
//        if(fragment == null) {
//            fragment = new MessageFragment();
//        }
//        view.switchFragment(fragment);
        view.hideFragment();
        view.showFragment(fragment);
        view.setTabUnCheck();
        view.setMessageTab(true);
    }

    @Override
    public void showUserFragment(Fragment fragment) {
//        if(fragment == null) {
//            fragment = new UserFragment();
//        }
//        view.switchFragment(fragment);
        view.hideFragment();
        view.showFragment(fragment);
        view.setTabUnCheck();
        view.setUserTab(true);
    }

    @Override
    public void exit() {
        if (( System.currentTimeMillis() - exitTime ) > 2000) {
            // 应用名
            String applicationName = context.getResources().getString(R.string.app_name);
            String msg = context.getResources().getString(R.string.exit) + applicationName;
            // ToastUtil.toastShort(msg);
            view.showToast(msg);
            exitTime = System.currentTimeMillis();
        } else {
            view.removeAllActivity();
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
            //  finish();
            System.exit(0);
        }
    }

    @Override
    public void initConfigFile(String filePaht, String fileName) {
//        //创建文件夹
//        File config_dir = new File(filePaht);
//
//        Config m_config = null;
//        File xmlFile = new File(config_dir, fileName);
//        try {
//            InputStream inputStream = new FileInputStream(xmlFile);
//            m_config = ConfigXmlUtils.getConfig(inputStream);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        if (m_config == null) {
//            m_config = new Config();
//            String language = Locale.getDefault().getLanguage();
//            if (language.endsWith("en")) {
//                Constants.Language = "en";
//
//                m_config.setLanguage(language);
//            }
////            else if(){
////
////                m_config.setLanguage(language);
////            }
//        }

        String SPLanguage = (String) SPUtils.getParam(context,Constants.Language,"");
        if(SPLanguage.equals("")) {
            String language = Locale.getDefault().getLanguage();
            if(language.endsWith("en")) {
                SPUtils.setParam(context,Constants.Language,"en");
            }else if (language.endsWith("es")) {
                SPUtils.setParam(context,Constants.Language,"es");
            }else if (language.endsWith("fr")) {
                SPUtils.setParam(context,Constants.Language,"fr");
            }else if (language.endsWith("de")) {
                SPUtils.setParam(context,Constants.Language,"de");
            }else if (language.endsWith("it")) {
                SPUtils.setParam(context,Constants.Language,"it");
            }else if (language.endsWith("ru")) {
                SPUtils.setParam(context,Constants.Language,"ru");
            }else if (language.endsWith("ja")) {
                SPUtils.setParam(context,Constants.Language,"ja");
            }else {
                SPUtils.setParam(context,Constants.Language,"en");
            }
        }


    }

    @Override
    public void initColorConfigFile(String filePaht, String fileName) {
        //创建文件夹
        File config_dir = new File(filePaht);

        File xmlFile = new File(config_dir, fileName);

        if (!xmlFile.exists()) {
            //复制文件到sd卡下
            try {
                InputStream inputStream = context.getAssets().open(fileName);
                FileUtils.CopySdcardFile(inputStream,filePaht + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        else {
//            try {
//                InputStream inputStream = context.getAssets().open(fileName);
//                int assets_version = ColorXmlUtils.getColorConfigVersion(inputStream);
//                LogUtils.e("assets version:" + assets_version);
//
//                InputStream inputStreamLocalFile = new FileInputStream(xmlFile);
//                int local_version = ColorXmlUtils.getColorConfigVersion(inputStreamLocalFile);
//                LogUtils.e("local version:" + local_version);
//                if(assets_version > local_version) {
//                    File localFile = new File(filePaht + fileName);
//                    localFile.delete();
//                    if (!xmlFile.exists()) {
//                        LogUtils.e("delete file");
//                    }
//                    FileUtils.CopySdcardFile(inputStream,filePaht + fileName);
//                    LogUtils.e("copy file");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        //读取文件内容
        File file = new File(config_dir, fileName);
        try {
            InputStream inputStream = new FileInputStream(file);
            Constants.colorConfigList.clear();
            Constants.colorConfigList.addAll(ColorXmlUtils.getColorConfig(inputStream));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Override
    public void checkPath() {

    }

    @Override
    public void switchLanguage() {
        String languageCode = model.getLanguage();
        if(!languageCode.equals("")) {
            model.switchLanguage(languageCode);
            LanguageEvent event = new LanguageEvent();
            event.msg = "do it";
            EventBus.getDefault().post(event);
        }else {
            String language = Locale.getDefault().getLanguage();
            if(language.endsWith("en")) {
                SPUtils.setParam(context,Constants.Language,"en");
            }else if (language.endsWith("es")) {
                SPUtils.setParam(context,Constants.Language,"es");
            }else if (language.endsWith("fr")) {
                SPUtils.setParam(context,Constants.Language,"fr");
            }else if (language.endsWith("de")) {
                SPUtils.setParam(context,Constants.Language,"de");
            }else if (language.endsWith("it")) {
                SPUtils.setParam(context,Constants.Language,"it");
            }else if (language.endsWith("ru")) {
                SPUtils.setParam(context,Constants.Language,"ru");
            }else if (language.endsWith("ja")) {
                SPUtils.setParam(context,Constants.Language,"ja");
            }else {
                SPUtils.setParam(context,Constants.Language,"en");
            }
        }

    }
}
