package com.rbu.lamphouse.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.utils.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 9:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public abstract class BaseActivity extends Activity implements BaseView{

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId()); //设置布局id
        //状态栏颜色设置 21（5.0以上）
        StatusBarUtils.setWindowStatusBarColor(this, R.color.headbg);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initView();  //初始化view
        initData();  //setData
    }



    /**
     * 初始化布局文件,设置布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 必要时，自己可以重写该方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            finish();
            overridePendingTransition(R.anim.anim_previous_in, R.anim.anim_previous_out);
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //        //隐藏软键盘
        //        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    //上一页activity跳转的动画
    public void preActivity(Class clazz) {
        Intent intent = new Intent(BaseActivity.this, clazz);
        startActivity(intent);
        //activity切换动画
        //        overridePendingTransition(R.anim.pre_enteranim, R.anim.pre_exitanim);
        // 关闭当前activity
        finish();
    }

    //下一页activity跳转的动画
    public void nextActivity(Class clazz) {
        Intent intent = new Intent(BaseActivity.this, clazz);
        startActivity(intent);
        //        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        // 关闭当前activity
        finish();
    }

    //下一页activity跳转的动画
    public void nextActivityNoFinish(Class clazz) {
        Intent intent = new Intent(BaseActivity.this, clazz);
        startActivity(intent);
        //        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

}
