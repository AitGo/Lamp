package com.rbu.lamphouse.device.persenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.search.SearchResult;
import com.rbu.lamphouse.R;
import com.rbu.lamphouse.base.Constants;
import com.rbu.lamphouse.device.model.DeviceModel;
import com.rbu.lamphouse.device.model.DeviceModelImpl;
import com.rbu.lamphouse.device.view.DeviceView;
import com.rbu.lamphouse.diagnose.BluetoothEntity;
import com.rbu.lamphouse.utils.BleUtils;

import java.util.List;

import static com.inuker.bluetooth.library.Constants.STATUS_CONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DEVICE_CONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DEVICE_CONNECTING;
import static com.inuker.bluetooth.library.Constants.STATUS_DEVICE_DISCONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DEVICE_DISCONNECTING;
import static com.inuker.bluetooth.library.Constants.STATUS_DISCONNECTED;


/**
 * @创建者 liuyang
 * @创建时间 2018/5/30 18:52
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class DevicePersenterImpl implements DevicePersenter {

    private Context     mContext;
    private DeviceView  mView;
    private DeviceModel mModel;
    private BluetoothClient mClient;

    public DevicePersenterImpl(Context context, DeviceView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new DeviceModelImpl(context);
        mClient = BleUtils.getInstanceClient(context);
    }


    @Override
    public void search(BluetoothClient mClient) {

        if (mModel.checkBLEFeature()) {
            if (mClient.isBluetoothOpened()) {

                mView.BluetoothScan();
            }else {
                //没有开启蓝牙
                mClient.openBluetooth();
//                mView.showProgress("");
                mView.BluetoothScan();
            }
        }
    }


    //回调函数
    @Override
    public void deviceFound(SearchResult device,List<BluetoothEntity> unConnList) {

        if(Constants.BluetoothLightName.equals(device.getName())) {
            BluetoothEntity entity = new BluetoothEntity();
            entity.setBluetoothName(device.getName());
            entity.setBluetoothMac(device.getAddress());
            unConnList.add(entity);
            mModel.array_unique(unConnList);

            mView.setUnConnListvisibility(View.VISIBLE);
            mView.notifyUnConnList();
        }
    }

    @Override
    public void deviceConn(String mac,int position) {
        mClient.registerConnectStatusListener(mac, mBleConnectStatusListener);
        mView.showProgress("");
        mView.BluetoothConn(mac,position);
    }

    @Override
    public void deviceConnResponse(int code,int position) {
        if(code == Constants.BluetoothState_DeviceConnecting) {
            mView.dismissProgress();
            mView.showDialog(mContext.getString(R.string.connectionsuccess));
            List<BluetoothEntity> connList = mView.getConnList();
            List<BluetoothEntity> unConnList = mView.getUnConnList();
            connList.clear();
            connList.add(unConnList.get(position));
            unConnList.remove(position);

            mView.notifyConnList();
            mView.notifyUnConnList();
            if(unConnList.size() == 0) {
                mView.setUnConnListvisibility(View.GONE);
            }
            mView.setConnListVisibility(View.VISIBLE);
            mModel.saveBluetoothMac(connList.get(0).getBluetoothMac());

            mModel.setAutoConn(mModel.getAutoConn());
            mModel.setAutoDisconn(mModel.getAutoDisconn());
            if(!"".equals(mModel.getOnTime())) {
                mModel.setTimerOn(mModel.getTimerOn(),mModel.getOnTime());
            }
            if(!"".equals(mModel.getOffTime())) {
                mModel.setTimerOff(mModel.getTimerOff(),mModel.getOffTime());
            }

        }else {
            mView.dismissProgress();
            mView.showDialog(mContext.getString(R.string.connectionfail));
        }

    }

    @Override
    public void stopConn(String mac, int position) {
//        mClient.unregisterConnectStatusListener(mac, mBleConnectStatusListener);
        mModel.BluetoothDisConn(mac);
        List<BluetoothEntity> connList = mView.getConnList();
        List<BluetoothEntity> unConnList = mView.getUnConnList();
        unConnList.add(connList.get(position));
        connList.remove(position);
        mView.notifyConnList();
        mView.notifyUnConnList();

        mView.setConnListVisibility(View.GONE);
        mView.setUnConnListvisibility(View.VISIBLE);

    }



    private final BleConnectStatusListener mBleConnectStatusListener = new BleConnectStatusListener() {

        @Override
        public void onConnectStatusChanged(String mac, int status) {
            if (status == STATUS_CONNECTED) {

            } else if (status == STATUS_DISCONNECTED) {
                List<BluetoothEntity> connList = mView.getConnList();
                List<BluetoothEntity> unConnList = mView.getUnConnList();
                if(connList.size() > 0) {
                    unConnList.add(connList.get(0));
                    connList.remove(0);
                    mView.notifyConnList();
                    mView.notifyUnConnList();

                    mView.setConnListVisibility(View.GONE);
                    mView.setUnConnListvisibility(View.VISIBLE);
                }

            } else if (status == STATUS_DEVICE_DISCONNECTING) {

            } else if (status == STATUS_DEVICE_DISCONNECTED) {

            } else if (status == STATUS_DEVICE_CONNECTED) {

            } else if (status == STATUS_DEVICE_CONNECTING) {

            }
        }
    };

}
