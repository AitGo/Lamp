package com.rbu.lamphouse.main.model;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 15:25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface MainModel {

    void copyColorConfig(String fromFile,String toFile);

    void newFilePath(String filePath);

    void switchLanguage(String language);

    String getLanguage();
}
