package com.rbu.lamphouse.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.MyBaseAdapter;
import com.rbu.lamphouse.diagnose.ColorRGB;
import com.rbu.lamphouse.utils.ColorUtils;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/1 16:56
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GridViewAdapter extends MyBaseAdapter{

    private Context        mContext;
    private List<ColorRGB> mList;

    public GridViewAdapter(Context context,List list) {
        super(list);
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if(convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid,null);
            holder.image = convertView.findViewById(R.id.iv_color);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.image.setBackgroundColor(ColorUtils.getColorFromRGB(mList.get(position).getR()
                ,mList.get(position).getG()
                ,mList.get(position).getB()));
        return convertView;
    }

    class Holder {
        public ImageView image;
    }
}
