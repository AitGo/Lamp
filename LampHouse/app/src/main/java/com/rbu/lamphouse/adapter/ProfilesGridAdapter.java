package com.rbu.lamphouse.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.diagnose.ColorConfig;
import com.rbu.lamphouse.utils.ColorUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 10:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProfilesGridAdapter extends BaseAdapter{

    private     Context       mContext;
    private List<ColorConfig> mList;

    private Integer[] picIds = {R.mipmap.berath,R.mipmap.sleep,R.mipmap.romantic,R.mipmap.reading};
    private String[] names = {"breath","sleep","romantic","reading"};

    private Integer addId = R.mipmap.allornone;
    private String addName = "all or none";


    public ProfilesGridAdapter(Context context, List<ColorConfig> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size() + 1;
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_profiles,null);
            holder.image = convertView.findViewById(R.id.iv_pic);
            holder.text = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        if(position < 4) {
            holder.image.setImageResource(picIds[position]);
            holder.text.setText(names[position]);
        } else if(position >= 4 && position < mList.size()){
            int color = ColorUtils.getColorFromRGB(Integer.valueOf(mList.get(position).getR())
                    ,Integer.valueOf(mList.get(position).getG())
                    ,Integer.valueOf(mList.get(position).getB()));
            Bitmap bitmap = Bitmap.createBitmap(118, 118,
                    Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(color);//填充颜色
            holder.image.setImageBitmap(bitmap);
            holder.text.setText(mList.get(position).getName());
        } else if(position == mList.size()) {
            holder.image.setImageResource(addId);
            holder.text.setText(addName);
        }

        return convertView;
    }

    class Holder {
        public CircleImageView image;
        public TextView        text;
    }
}
