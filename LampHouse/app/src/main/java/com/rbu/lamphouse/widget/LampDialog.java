package com.rbu.lamphouse.widget;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rbu.lamphouse.R;


public class LampDialog {
    private Context             cxt;
    private AlertDialog         mRadioDialog;
    private AlertDialog.Builder mDialog;
    private View mV;
    private boolean isShow = false;
    private AppTextView tv_title,tv_content;

    public LampDialog(Context context) {
        cxt = context;
        mDialog = new AlertDialog.Builder(cxt, R.style.Translucent_NoTitle);
        mDialog.setCancelable(true);
        mV = View.inflate(cxt, R.layout.item_text_dialog, null);
        tv_title = (AppTextView) mV.findViewById(R.id.lamp_dialog_title);
        tv_content = (AppTextView) mV.findViewById(R.id.lamp_dialog_content);
        mDialog.setView(mV);
    }

    public void setTitle(String title) {
        tv_title.setTextWithString(title);
    }

    public void setContent(String content) {
        tv_content.setTextWithString(content);
    }


    public void showDialog() {
        if (!isShow) {
            if (mRadioDialog == null) {
                mRadioDialog = mDialog.show();
            } else {
                mRadioDialog.show();
            }
            isShow = true;
        }
    }

    public synchronized void dismiss() {
        if (mRadioDialog != null && isShow) {
            mRadioDialog.dismiss();
            isShow = false;
        }
    }



}
