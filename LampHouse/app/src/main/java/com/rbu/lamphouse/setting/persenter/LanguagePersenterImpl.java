package com.rbu.lamphouse.setting.persenter;

import android.content.Context;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.diagnose.Language;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.setting.model.LanguageModel;
import com.rbu.lamphouse.setting.model.LanguageModelImpl;
import com.rbu.lamphouse.setting.view.LanguageView;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Locale;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/4 17:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class LanguagePersenterImpl implements LanguagePersenter {

    private Context mContext;
    private LanguageView mView;
    private LanguageModel mModel;

    public LanguagePersenterImpl(Context context,LanguageView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new LanguageModelImpl(context);
    }

    @Override
    public void finishActivity() {
        mView.finishActivity();
    }

    @Override
    public void initListData(List<Language> list) {
        list.add(new Language(mContext.getString(R.string.en),"en",0));
        list.add(new Language(mContext.getString(R.string.es),"es",0));
        list.add(new Language(mContext.getString(R.string.fr),"fr",0));
        list.add(new Language(mContext.getString(R.string.de),"de",0));
        list.add(new Language(mContext.getString(R.string.it),"it",0));
        list.add(new Language(mContext.getString(R.string.ru),"ru",0));
        list.add(new Language(mContext.getString(R.string.ja),"ja",0));

        String local = mModel.getLanguageConfig();
        for(Language language : list) {
            if(language.getLanguageCode().equals(local)) {
                language.setState(1);
            }
        }
    }

    @Override
    public void setState(List<Language> list,int position) {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setState(0);
            if(i == position) {
                list.get(position).setState(1);
                mModel.saveLanguageConfig(list.get(position).getLanguageCode());
                mModel.switchLanguage(list.get(position).getLanguageCode());
                LanguageEvent event = new LanguageEvent();
                event.msg = "do it";
                EventBus.getDefault().post(event);
            }
        }
    }
}
