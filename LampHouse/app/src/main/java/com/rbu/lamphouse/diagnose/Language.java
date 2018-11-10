package com.rbu.lamphouse.diagnose;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/6 17:29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Language {
    private String language;
    private String languageCode;

    private int state;//选中状态，0：未选中 1：选中

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Language(String language,String code,int state) {
        this.language = language;
        this.languageCode = code;
        this.state = state;
    }
}
