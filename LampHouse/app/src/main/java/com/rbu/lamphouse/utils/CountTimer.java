package com.rbu.lamphouse.utils;

import android.content.Context;
import android.os.CountDownTimer;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.widget.AppButton;

/**
 * 倒计时器
 */
public class CountTimer extends CountDownTimer {
    private AppButton mView;
    private Context mContext;
    public CountTimer(Context context, AppButton button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mContext = context;
        this.mView = button;
    }

    //计时过程
    @Override
    public void onTick(long l) {
        //防止计时过程中重复点击
        mView.setClickable(false);
        mView.setTextWithString(l/1000+"s");
        mView.setBackgroundColor(mContext.getResources().getColor(R.color.bg_seek_normal));
    }

    //计时完毕的方法
    @Override
    public void onFinish() {
        //重新给Button设置文字
        mView.setTextWithString(mContext.getString(R.string.tkjn));
        //设置可点击
        mView.setClickable(true);
        mView.setBackgroundColor(mContext.getResources().getColor(R.color.bg_blue));
    }

}
