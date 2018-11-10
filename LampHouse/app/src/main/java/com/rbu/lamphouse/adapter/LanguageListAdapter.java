package com.rbu.lamphouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.MyBaseAdapter;
import com.rbu.lamphouse.diagnose.ColorRGB;
import com.rbu.lamphouse.diagnose.Language;
import com.rbu.lamphouse.utils.ColorUtils;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/6 17:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LanguageListAdapter extends MyBaseAdapter {

    private Context        mContext;
    private List<Language> mList;

    public LanguageListAdapter(Context context,List<Language> list) {
        super(list);
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_language,null);
            holder.language = convertView.findViewById(R.id.item_language);
            holder.state = convertView.findViewById(R.id.item_state);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.language.setText(mList.get(position).getLanguage());
        if(mList.get(position).getState() == 1) {
            holder.state.setVisibility(View.VISIBLE);
        }else {
            holder.state.setVisibility(View.GONE);
        }

        return convertView;
    }

    class Holder{
        public TextView language;
        public ImageView state;
    }
}
