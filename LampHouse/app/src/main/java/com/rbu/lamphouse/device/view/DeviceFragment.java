package com.rbu.lamphouse.device.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.receiver.listener.BluetoothBondListener;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.adapter.DeviceConnAdapter;
import com.rbu.lamphouse.adapter.DeviceUnConnAdapter;
import com.rbu.lamphouse.base.BaseFragment;
import com.rbu.lamphouse.device.persenter.DevicePersenter;
import com.rbu.lamphouse.device.persenter.DevicePersenterImpl;
import com.rbu.lamphouse.diagnose.BluetoothEntity;
import com.rbu.lamphouse.event.LanguageEvent;
import com.rbu.lamphouse.utils.BleUtils;
import com.rbu.lamphouse.utils.LogUtils;
import com.rbu.lamphouse.utils.ViewUtils;
import com.rbu.lamphouse.widget.LampDialog;
import com.rbu.lamphouse.widget.LampProgressDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.inuker.bluetooth.library.Constants.STATUS_CONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DISCONNECTED;

/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 10:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class DeviceFragment extends BaseFragment implements DeviceView, View.OnClickListener, AdapterView.OnItemClickListener {

    private DevicePersenter mPersenter;

    private BluetoothClient mClient;
    private Context         mContext;
    private LinearLayout    ll_search;
    private ListView        lv_conn, lv_unconn;
    private List<BluetoothEntity> mBluetoothEntities = new ArrayList<>();
    private List<BluetoothEntity> unConnList = new ArrayList<>();
    private List<BluetoothEntity> connList = new ArrayList<>();

    private DeviceConnAdapter connAdapter;
    private DeviceUnConnAdapter unConnAdapter;

    private LampDialog dialog;
    private LampProgressDialog mProgressDialog;

    private View inflate;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device;
    }

    @Override
    protected void initView(View inflate) {
        this.inflate = inflate;
        mContext = getContext();
        mPersenter = new DevicePersenterImpl(getContext(), this);
        ll_search = inflate.findViewById(R.id.device_search);
        lv_conn = inflate.findViewById(R.id.device_lv_conn);
        lv_unconn = inflate.findViewById(R.id.device_lv_unconn);

        dialog = new LampDialog(mContext);


        ll_search.setOnClickListener(this);
        lv_conn.setOnItemClickListener(this);
        lv_unconn.setOnItemClickListener(this);

    }


    @Override
    protected void initData() {
        mClient = BleUtils.getInstanceClient(mContext);

        unConnAdapter = new DeviceUnConnAdapter(mContext,unConnList);
        connAdapter = new DeviceConnAdapter(mContext,connList);
        lv_conn.setAdapter(connAdapter);
        lv_unconn.setAdapter(unConnAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
        mPersenter.search(mClient);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            //切换到device页面可见时搜索蓝牙
//            mPersenter.search(mClient);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onStringEvent(LanguageEvent event) {
        ViewUtils.updateViewLanguage(inflate.findViewById(android.R.id.content));
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        LogUtils.e("onRequestPermissionsResult: " + requestCode);
        //        if (requestCode == REQUEST_ACCESS_COARSE_LOCATION) {
        //            if (permissions[0] .equals(Manifest.permission.ACCESS_COARSE_LOCATION)
        //                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //                // 用户同意使用该权限
        //            } else {
        //                // 用户不同意，向用户展示该权限作用
        //                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
        //                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
        //                    //showTipDialog("用来扫描附件蓝牙设备的权限，请手动开启！");
        //                    return;
        //                }
        //            }
        //        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_search:
                showProgress("");
                mPersenter.search(mClient);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.device_lv_conn:
                //断开连接
                mPersenter.stopConn(connList.get(position).getBluetoothMac(),position);

                break;

            case R.id.device_lv_unconn:

                //连接蓝牙，弹出progress
                mPersenter.deviceConn(unConnList.get(position).getBluetoothMac(),position);

                break;
        }
    }



    @Override
    public void BluetoothScan() {

        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 1)   // 先扫BLE设备1次，每次3s
                //                    .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
                //                        .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();

        mClient.search(request, new SearchResponse() {

            @Override
            public void onSearchStarted() {

            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                mPersenter.deviceFound(device,unConnList);
            }

            @Override
            public void onSearchStopped() {
                dismissProgress();
            }

            @Override
            public void onSearchCanceled() {
                dismissProgress();
            }
        });
    }

    @Override
    public void BluetoothConn(final String mac, final int position) {
        mClient.connect(mac, new BleConnectResponse() {

            @Override
            public void onResponse(int code, BleGattProfile data) {
                mPersenter.deviceConnResponse(code ,position);
            }
        });
    }



    @Override
    public void setConnListVisibility(int visibility) {
        lv_conn.setVisibility(visibility);
    }

    @Override
    public void setUnConnListvisibility(int visibility) {
        lv_unconn.setVisibility(visibility);
    }

    @Override
    public List<BluetoothEntity> getConnList() {
        return connList;
    }

    @Override
    public List<BluetoothEntity> getUnConnList() {
        return unConnList;
    }


    @Override
    public void notifyConnList() {
        connAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyUnConnList() {
        unConnAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new LampProgressDialog(mContext,msg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showDialog(String msg) {
        dialog.setContent(msg);
        dialog.showDialog();
    }

}
