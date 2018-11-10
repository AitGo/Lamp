package com.rbu.lamphouse.home.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.GridViewAdapter;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.event.UIChangedEvent;
import com.rbu.lamphouse.home.persenter.HomePersenter;
import com.rbu.lamphouse.home.persenter.HomePersenterImpl;
import com.rbu.lamphouse.setting.view.SettingActivity;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.SPUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.ColorPickerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 10:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeView, ColorPickerView.OnColorChangedListener, SeekBar.OnSeekBarChangeListener, View.OnFocusChangeListener
        //        , AdapterView.OnItemClickListener
{

    private HomePersenter mPersenter;
    private LinearLayout  home_setting, home_switch;
    private com.rbu.lamphouse.widget.ColorPickerView picker;
    //    private GridView mGridView;
    private EditText et_r, et_g, et_b;
    private SeekBar  mSeekBar;
    private TextView tv_light;

    private ImageView white, yellow, red, blue, green, skyblue, purple, orange;
    private ImageView[] mImageViews = {white, yellow, red, blue, green, skyblue, purple, orange};

    private View inflate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View inflate) {
        this.inflate = inflate;
        mPersenter = new HomePersenterImpl(getContext(), this);
        home_setting = (LinearLayout) inflate.findViewById(R.id.home_setting);
        home_switch = (LinearLayout) inflate.findViewById(R.id.home_switch);
        picker = (ColorPickerView) inflate.findViewById(R.id.home_picker);
        //        mGridView = inflate.findViewById(R.id.home_gridview);
        et_r = inflate.findViewById(R.id.home_et_r);
        et_g = inflate.findViewById(R.id.home_et_g);
        et_b = inflate.findViewById(R.id.home_et_b);
        mSeekBar = inflate.findViewById(R.id.home_seek_light);
        tv_light = inflate.findViewById(R.id.home_tv_light);
        white = inflate.findViewById(R.id.iv_white);
        yellow = inflate.findViewById(R.id.iv_yellow);
        red = inflate.findViewById(R.id.iv_red);
        blue = inflate.findViewById(R.id.iv_blue);
        green = inflate.findViewById(R.id.iv_green);
        skyblue = inflate.findViewById(R.id.iv_skyblue);
        purple = inflate.findViewById(R.id.iv_purple);
        orange = inflate.findViewById(R.id.iv_orange);

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
        home_setting.setOnClickListener(this);
        home_switch.setOnClickListener(this);

//        et_r.setOnFocusChangeListener(this);

//        et_r.addTextChangedListener(new TextWatcher() {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before, int count) {
        //                mPersenter.RChanged(s);
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s) {
        //
        //            }
        //        });
        //
        //        et_g.addTextChangedListener(new TextWatcher() {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before, int count) {
        //                mPersenter.GChanged(s);
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s) {
        //
        //            }
        //        });
        //
        //        et_b.addTextChangedListener(new TextWatcher() {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before, int count) {
        //                mPersenter.BChanged(s);
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s) {
        //
        //            }
        //        });

    }


    @Override
    protected void initData() {
        //        GridViewAdapter adapter = new GridViewAdapter(getContext(), Constants.getHomeColor());
        //        mGridView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UIChangedEvent event) {
        if(event.getMsg() == UIChangedEvent.MSG_LAMP_ON) {
            mPersenter.lampOFF();
        }else if(event.getMsg() == UIChangedEvent.MSG_LAMP_OFF) {
            mPersenter.lampON();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_setting:
                mPersenter.startSettingActivity(new SettingActivity());
                break;
            case R.id.home_switch:
                mPersenter.checkLamp();
                break;
            case R.id.iv_white:
                mPersenter.setRGB(0);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_yellow:
                mPersenter.setRGB(1);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_red:
                mPersenter.setRGB(2);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_blue:
                mPersenter.setRGB(3);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_green:
                mPersenter.setRGB(4);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_skyblue:
                mPersenter.setRGB(5);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_purple:
                mPersenter.setRGB(6);
                mPersenter.setLampRGB();
                break;
            case R.id.iv_orange:
                mPersenter.setRGB(7);
                mPersenter.setLampRGB();
                break;

        }
    }

    @Override
    public void onColorChanged(int color) {

        mPersenter.setLampRGB(color);
        mPersenter.setEditText(color);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setTvLight(progress + "%");
        mPersenter.setLampLight(progress);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.home_et_r:
                if(hasFocus) {
                    mPersenter.setState(false);
                }

                break;

            case R.id.home_et_g:
                if(hasFocus) {
                    mPersenter.setState(false);
                }

                break;

            case R.id.home_et_b:
                if(hasFocus) {
                    mPersenter.setState(false);
                }

                break;

        }
    }

    //    @Override
    //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //
    //    }

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
    public int getEt_R() {
        return Integer.valueOf(et_r.getText().toString());
    }

    @Override
    public int getEt_G() {
        return Integer.valueOf(et_g.getText().toString());
    }

    @Override
    public int getEt_B() {
        return Integer.valueOf(et_b.getText().toString());
    }

    @Override
    public void setTvLight(String text) {
        tv_light.setText(text);
    }

    @Override
    public void lostFocus() {
        et_r.clearFocus();
        et_g.clearFocus();
        et_b.clearFocus();
        ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



}
