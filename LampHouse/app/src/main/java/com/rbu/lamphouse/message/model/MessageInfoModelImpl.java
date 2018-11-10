package com.rbu.lamphouse.message.model;

import android.content.Context;

import com.google.gson.Gson;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.diagnose.MessageInfoEntity;
import com.rbu.lamphouse.diagnose.MsgInfoNetEntity;
import com.rbu.lamphouse.utils.SPUtils;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/25 9:33
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MessageInfoModelImpl implements MessageInfoModel {
    private Context mContext;

    public MessageInfoModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean checkMsg(Gson gson, String response) {
        MsgInfoNetEntity data = gson.fromJson(response,MsgInfoNetEntity.class);
        if(data.getStatus() == Constants.successState) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getToken() {
        return (String) SPUtils.getParam(mContext,Constants.token,"");
    }

    @Override
    public String reSizeImgJaveScript() {
        String javascript = "javascript:function ResizeImages() {" +
                "var myimg,oldwidth;" +
                "var maxwidth = document.body.clientWidth;" +
                "for(i=0;i <document.images.length;i++){" +
                "myimg = document.images[i];" +
                //                "if(myimg.width > maxwidth){" +
                //                "oldwidth = myimg.width;" +
                "myimg.width = maxwidth;" +
                //                "}" +
                "}" +
                "}";
        return javascript;
    }
}
