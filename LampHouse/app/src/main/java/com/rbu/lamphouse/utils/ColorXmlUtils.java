package com.rbu.lamphouse.utils;

import android.graphics.Color;
import android.util.Xml;

import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.diagnose.Config;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/31 11:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ColorXmlUtils {

    private final static String Config = "Config";
    private final static String Encoding = "UTF-8";
    private final static String TagConfig = "config";
    private final static String version = "version";
    private final static String TagName  = "color";
    private final static String Name     = "Name";//配置项名称
    private final static String R        = "R";
    private final static String G        = "G";
    private final static String B        = "B";
    private final static String Light    = "Light";//灯箱亮度
//    private final static String Type     = "Type";//配置类型，main：主界面中的配置 setting：设置中的情景模式,添加只能添加到情景模式中

    public static List<ColorConfig> getColorConfig(InputStream xml) throws Exception {
        ArrayList<ColorConfig> colorConfiglist = new ArrayList<>();
        ColorConfig str = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, Encoding); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {

            switch (event) {

                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    if (TagName.equals(pullParser.getName())) {
                        //读取colorconfig，添加到list
                        str = new ColorConfig();
                        str.setName(pullParser.getAttributeValue(null, Name));
                        str.setR(pullParser.getAttributeValue(null, R));
                        str.setG(pullParser.getAttributeValue(null, G));
                        str.setB(pullParser.getAttributeValue(null, B));
                        str.setLight(pullParser.getAttributeValue(null, Light));
//                        str.setType(pullParser.getAttributeValue(null, Type));
                        colorConfiglist.add(str);
                    }

                    break;

                case XmlPullParser.END_TAG:
                    break;

            }
            event = pullParser.next();
        }

        return colorConfiglist;
    }

    public static int getColorConfigVersion(InputStream xml) throws Exception {
        int versionCode = 1;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, Encoding); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {

            switch (event) {

                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    if (TagConfig.equals(pullParser.getName())) {
                        versionCode = Integer.parseInt(pullParser.getAttributeValue(null,version));
                    }

                    break;

                case XmlPullParser.END_TAG:
                    break;

            }
            event = pullParser.next();
        }
        return versionCode;
    }

    /**
     * 保存数据到xml文件中
     * @param
     * @param out
     * @throws Exception
     */
    public static void saveConfig(List<ColorConfig> colorConfigList, OutputStream out) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, Encoding);
        serializer.startDocument(Encoding, true);
        serializer.startTag(null, Config);
        for(ColorConfig colorConfig : colorConfigList) {
            serializer.startTag(null,TagName);

            if(colorConfig.getName() != null) {
                serializer.attribute(null,Name,colorConfig.getName());
            }
            if(colorConfig.getR() != null) {
                serializer.attribute(null,R,colorConfig.getR());
            }
            if(colorConfig.getG() != null) {
                serializer.attribute(null,G,colorConfig.getG());
            }
            if(colorConfig.getB() != null) {
                serializer.attribute(null,B,colorConfig.getB());
            }
            if(colorConfig.getLight() != null) {
                serializer.attribute(null,Light,colorConfig.getLight());
            }
//            if(colorConfig.getType() != null) {
//                serializer.attribute(null,Type,colorConfig.getType());
//            }

            serializer.endTag(null, TagName);
        }

        serializer.endTag(null, Config);
        serializer.endDocument();
        out.flush();
        out.close();
    }


//    public List<ColorConfig> getColorList(List<ColorConfig> colorConfigList, String type) {
//        ArrayList<ColorConfig> list = new ArrayList<>();
//        if (Constants.COLOR_TYPE_MAIN.equals(type)) {
//            for (ColorConfig colorConfig : colorConfigList) {
//                if (Constants.COLOR_TYPE_MAIN.equals(colorConfig.getType())) {
//                    list.add(colorConfig);
//                }
//            }
//        } else if (Constants.COLOR_TYPE_SETTING.equals(type)) {
//            for (ColorConfig colorConfig : colorConfigList) {
//                if (Constants.COLOR_TYPE_SETTING.equals(colorConfig.getType())) {
//                    list.add(colorConfig);
//                }
//            }
//        }
//
//
//        return list;
//    }

//    public List<ColorConfig> addColorList(List<ColorConfig> mainList, List<ColorConfig> settingList) {
//        ArrayList<ColorConfig> list = new ArrayList<>();
//        list.addAll(mainList);
//        list.addAll(settingList);
//        return list;
//    }

}
