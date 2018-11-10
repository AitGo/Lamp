package com.rbu.lamphouse.setting.model;

import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.utils.ColorUtils;
import com.rbu.lamphouse.utils.ColorXmlUtils;

import java.io.OutputStream;
import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 16:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddProfilesModelImpl implements AddProfilesModel {

    public AddProfilesModelImpl() {

    }

    @Override
    public int getRGBFromColor(int color, String type) {
        return ColorUtils.getRGBFromColor(color,type);
    }

    @Override
    public void saveColorConfigXml(List<ColorConfig> colorConfigList, OutputStream outputStream) {
        try {
            ColorXmlUtils.saveConfig(colorConfigList,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
