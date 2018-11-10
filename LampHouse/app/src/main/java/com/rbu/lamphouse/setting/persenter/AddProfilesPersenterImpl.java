package com.rbu.lamphouse.setting.persenter;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.setting.model.AddProfilesModel;
import com.rbu.lamphouse.setting.model.AddProfilesModelImpl;
import com.rbu.lamphouse.setting.view.AddProfilesView;
import com.rbu.lamphouse.utils.ColorUtils;
import com.rbu.lamphouse.widget.LampEditDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 15:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddProfilesPersenterImpl implements AddProfilesPersenter {

    private Context mContext;
    private AddProfilesView mView;
    private AddProfilesModel mModel;

    private int r;
    private int g;
    private int b;

    public AddProfilesPersenterImpl(Context context,AddProfilesView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new AddProfilesModelImpl();
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }

    @Override
    public void addColorConfig(String input) {
        if("".equals(input)) {
            mView.showToast(mContext.getString(R.string.input_profile_name));
            return;
        }
        if("".equals(mView.getEt_R())
                && "".equals(mView.getEt_G())
                && "".equals(mView.getEt_B())) {
            mView.showToast(mContext.getString(R.string.select_color));
            return;
        }

        if(!"".equals(mView.getEt_R())
                && !"".equals(mView.getEt_G())
                && !"".equals(mView.getEt_B())
                && !"".equals(input)) {

            ColorConfig colorConfig = new ColorConfig();
            colorConfig.setR(mView.getEt_R());
            colorConfig.setG(mView.getEt_G());
            colorConfig.setB(mView.getEt_B());
            colorConfig.setLight(mView.getLight()+"");
            colorConfig.setName(input);
            Constants.colorConfigList.add(Constants.colorConfigList.size(),colorConfig);
            File file = new File(Constants.getConfigDir(mContext), Constants.COLOR_CONFIG_NAME);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                mModel.saveColorConfigXml(Constants.colorConfigList,outputStream);
                //更新profilesActivity的list
                EventBus.getDefault().post(new UIChangedEvent(UIChangedEvent.MSG_ADD_PROFILES));
                mView.finishActivity();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void saveColorConfig() {
        mView.showEditDialog();
    }

    @Override
    public void setRGB(int index) {
        r = Constants.home_r[index];
        g = Constants.home_g[index];
        b = Constants.home_b[index];
        mView.setEt_R(r);
        mView.setEt_G(g);
        mView.setEt_B(b);
    }

    @Override
    public void setEditText(int color) {
        mView.setEt_R(mModel.getRGBFromColor(color, ColorUtils.R));
        mView.setEt_G(mModel.getRGBFromColor(color, ColorUtils.G));
        mView.setEt_B(mModel.getRGBFromColor(color, ColorUtils.B));
    }
}
