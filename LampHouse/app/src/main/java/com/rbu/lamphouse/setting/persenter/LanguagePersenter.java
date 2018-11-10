package com.rbu.lamphouse.setting.persenter;

import com.rbu.lamphouse.diagnose.Language;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 17:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface LanguagePersenter {

    void finishActivity();

    void initListData(List<Language> list);

    void setState(List<Language> list,int position);
}
