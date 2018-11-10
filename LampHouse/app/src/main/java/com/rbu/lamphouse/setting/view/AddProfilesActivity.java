package com.rbu.lamphouse.setting.view;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.BaseActivity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.setting.persenter.AddProfilesPersenter;
import com.rbu.lamphouse.setting.persenter.AddProfilesPersenterImpl;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.ColorPickerView;
import com.rbu.lamphouse.widget.LampEditDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/6/5 15:11
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class AddProfilesActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ColorPickerView.OnColorChangedListener,AddProfilesView {

    private AddProfilesPersenter mPersenter;
    private LinearLayout  add_back, add_save;
    private com.rbu.lamphouse.widget.ColorPickerView picker;
    //    private GridView mGridView;
    private EditText                                 et_r, et_g, et_b;
    private SeekBar  mSeekBar;
    private TextView tv_light;

    private ImageView white, yellow, red, blue, green, skyblue, purple, orange;

    private LampEditDialog lampEditDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addprofiles;
    }

    @Override
    protected void initView() {
        mPersenter = new AddProfilesPersenterImpl(this,this);
        add_back = (LinearLayout) findViewById(R.id.add_back);
        add_save = (LinearLayout) findViewById(R.id.add_save);
        picker = (ColorPickerView) findViewById(R.id.add_picker);
        //        mGridView = findViewById(R.id.add_gridview);
        et_r = findViewById(R.id.add_et_r);
        et_g = findViewById(R.id.add_et_g);
        et_b = findViewById(R.id.add_et_b);
        mSeekBar = findViewById(R.id.add_seek_light);
        tv_light = findViewById(R.id.add_tv_light);
        white = findViewById(R.id.add_white);
        yellow = findViewById(R.id.add_yellow);
        red = findViewById(R.id.add_red);
        blue = findViewById(R.id.add_blue);
        green = findViewById(R.id.add_green);
        skyblue = findViewById(R.id.add_skyblue);
        purple = findViewById(R.id.add_purple);
        orange = findViewById(R.id.add_orange);

        //        mGridView.setOnItemClickListener(this);
        white.setOnClickListener(this);
        yellow.setOnClickListener(this);
        red.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        skyblue.setOnClickListener(this);
        purple.setOnClickListener(this);
        orange.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        picker.setColorChangedListener(this);
        add_back.setOnClickListener(this);
        add_save.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(findViewById(android.R.id.content));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_back:
                mPersenter.finishActivity();
                break;
            case R.id.add_save:
                mPersenter.saveColorConfig();
                break;
            case R.id.add_white:
                mPersenter.setRGB(0);
                break;
            case R.id.add_yellow:
                mPersenter.setRGB(1);
                break;
            case R.id.add_red:
                mPersenter.setRGB(2);
                break;
            case R.id.add_blue:
                mPersenter.setRGB(3);
                break;
            case R.id.add_green:
                mPersenter.setRGB(4);
                break;
            case R.id.add_skyblue:
                mPersenter.setRGB(5);
                break;
            case R.id.add_purple:
                mPersenter.setRGB(6);
                break;
            case R.id.add_orange:
                mPersenter.setRGB(7);
                break;
        }
    }

    @Override
    public void onColorChanged(int color) {
        mPersenter.setEditText(color);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setTvLight(progress + "%");

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setEt_R(int r) {
        et_r.setText(r + "");
    }

    @Override
    public void setEt_G(int g) {
        et_g.setText(g + "");
    }

    @Override
    public void setEt_B(int b) {
        et_b.setText(b + "");
    }

    @Override
    public String getEt_R() {
        return et_r.getText().toString();
    }

    @Override
    public String getEt_G() {
        return et_g.getText().toString();
    }

    @Override
    public String getEt_B() {
        return et_b.getText().toString();
    }

    @Override
    public void setTvLight(String text) {
        tv_light.setText(text);
    }

    @Override
    public int getLight() {
        return mSeekBar.getProgress();
    }

    @Override
    public void showEditDialog() {
        LampEditDialog.Builder builder = new LampEditDialog.Builder(this);
        lampEditDialog = builder.setTitle(getString(R.string.prompt))
                .setEditHint(getString(R.string.inputhint))
                .setPositiveButton(getString(R.string.confirm), new LampEditDialog.ConfirmListener() {
                    @Override
                    public void onClick(String input) {
                        mPersenter.addColorConfig(input);
                        lampEditDialog.dismiss();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lampEditDialog.dismiss();
                    }
                })
                .create();
        lampEditDialog.show();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }
}
