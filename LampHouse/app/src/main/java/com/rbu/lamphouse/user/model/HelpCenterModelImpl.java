package com.rbu.lamphouse.user.model;

import android.content.Context;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/14 10:47
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HelpCenterModelImpl implements HelpCenterModel {
    private Context mContext;

    public HelpCenterModelImpl(Context context) {
        this.mContext = context;
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
