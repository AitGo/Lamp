package com.rbu.lamphouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.MyBaseAdapter;
import com.rbu.lamphouse.diagnose.BluetoothEntity;

import java.util.List;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/8 11:09
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceUnConnAdapter extends MyBaseAdapter {
    private Context                mContext;
    private  List<BluetoothEntity> mList;

    public DeviceUnConnAdapter(Context context,List list) {
        super(list);
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_unconn,null);
            holder.bluetoothName = convertView.findViewById(R.id.unconn_name);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.bluetoothName.setText(mList.get(position).getBluetoothName());

        return convertView;
    }

    class Holder{
        public TextView  bluetoothName;
    }
}
