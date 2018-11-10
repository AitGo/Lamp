package com.rbu.lamphouse.utils;

import java.io.InputStream;
import java.io.OutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;


import android.util.Xml;

import com.rbu.lamphouse.diagnose.Config;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 17:24
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ConfigXmlUtils {

    private final static String Encoding = "UTF-8";
    private final static String Config = "Config";
    private final static String Language = "Language";
    private final static String BlueToothName = "BlueToothName";
    private final static String BlueToothMac = "BlueToothMac";
    private final static String AutoConnected = "AutoConnected";
    private final static String AutoDisconnected = "AutoDisconnected";
    private final static String TimerOff = "TimerOff";
    private final static String OffTime = "OffTime";
    private final static String TimerOn = "TimeOn";
    private final static String OnTime = "OnTime";

    public static Config getConfig(InputStream xml) throws Exception {
        Config str = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, Encoding); //为Pull解释器设置要解析的XML数据
        int event = pullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT){

            switch (event) {

                case XmlPullParser.START_DOCUMENT:
                    str = new Config();
                    break;
                case XmlPullParser.START_TAG:
                    if (Language.equals(pullParser.getName())){
                        str.setLanguage(pullParser.nextText());
                    }
                    if (BlueToothName.equals(pullParser.getName())){
                        str.setBlueToothName(pullParser.nextText());
                    }
                    if (BlueToothMac.equals(pullParser.getName())){
                        str.setBlueToothMac(pullParser.nextText());
                    }
                    if (AutoConnected.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }
                    if (AutoDisconnected.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }
                    if (TimerOff.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }
                    if (OffTime.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }
                    if (TimerOn.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }
                    if (OnTime.equals(pullParser.getName())) {
                        str.setAutoConnected(pullParser.nextText());
                    }

                    break;

                case XmlPullParser.END_TAG:
                    break;

            }

            event = pullParser.next();
        }


        return str;
    }

    /**
     * 保存数据到xml文件中
     * @param
     * @param out
     * @throws Exception
     */
    public static void saveConfig(Config m_config, OutputStream out) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, Encoding);
        serializer.startDocument(Encoding, true);
        serializer.startTag(null, Config);
        if(m_config.getLanguage()!=null)
        {
            serializer.startTag(null, Language);
            serializer.text(m_config.getLanguage());
            serializer.endTag(null, Language);
        }

        if(m_config.getBlueToothName()!=null)
        {
            serializer.startTag(null, BlueToothName);
            serializer.text(m_config.getBlueToothName());
            serializer.endTag(null, BlueToothName);
        }
        if(m_config.getBlueToothMac()!=null)
        {
            serializer.startTag(null, BlueToothMac);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, BlueToothMac);
        }
        if(m_config.getAutoConnected()!=null)
        {
            serializer.startTag(null, AutoConnected);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, AutoConnected);
        }
        if(m_config.getAutoDisconnected()!=null)
        {
            serializer.startTag(null, AutoDisconnected);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, AutoDisconnected);
        }
        if(m_config.getTimerOff()!=null)
        {
            serializer.startTag(null, TimerOff);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, TimerOff);
        }
        if(m_config.getOffTime()!=null)
        {
            serializer.startTag(null, OffTime);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, OffTime);
        }
        if(m_config.getTimerOn()!=null)
        {
            serializer.startTag(null, TimerOn);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, TimerOn);
        }
        if(m_config.getOnTime()!=null)
        {
            serializer.startTag(null, OnTime);
            serializer.text(m_config.getBlueToothMac());
            serializer.endTag(null, OnTime);
        }

        serializer.endTag(null, Config);
        serializer.endDocument();
        out.flush();
        out.close();
    }
}

