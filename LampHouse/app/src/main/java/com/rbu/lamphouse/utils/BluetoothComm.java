package com.rbu.lamphouse.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;

public class BluetoothComm {
	
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private static BluetoothSocket mSocket = null;
	private static InputStream mInputStream = null;
	private static OutputStream mOutputStream = null;
	private static ConcurrentLinkedQueue<Byte> mQueue = new ConcurrentLinkedQueue<Byte>();
	private static boolean isAvailable = false;
	
	@SuppressLint("NewApi")
	public static boolean connect(String address) {
		if (mSocket == null)
		{
			BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
			if (adapter == null) return false;
			if (adapter.getState() != BluetoothAdapter.STATE_ON) return false;
			BluetoothDevice device = null;
			if (BluetoothAdapter.checkBluetoothAddress(address)) {
				device = adapter.getRemoteDevice(address);
			}
			if (device == null) return false;
			try {
				mSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);
				mInputStream = mSocket.getInputStream();
				mOutputStream = mSocket.getOutputStream();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				close();
				return false;
			}
			try {
				mSocket.connect();
				new Thread(){
					public void run() {
						try {
							mInputStream.available();
							isAvailable = true;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							isAvailable = false;
						}
					};
				}.start();
				
				long start = System.nanoTime();
				while ((System.nanoTime() - start) / 1000000 < 1000) {
					if (isAvailable) break;
					isAvailable = false;
				}
				
				new Thread() {
					public void run() {
						byte[] buff = new byte[2048];
						while (true) {
							try {
								if (isAvailable) {
									mInputStream.available();
								}
								int length = mInputStream.read(buff, 0, buff.length);
								for (int i = 0; i < length; i++) {
									mQueue.add(new Byte(buff[i]));
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								close();
								return;
							}
						}
					};
				}.start();
			} catch (IOException e) {
				e.printStackTrace();
				close();
				return false;
			}
		}
		return true;
	}
	
	public static boolean sendData(byte[] data) {
		if (!isConnected()) return false;
		try {
			mOutputStream.write(data);
			mOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
			return false;
		}
		return true;
	}
	
	public static char getData() {
		if (mQueue.isEmpty()) return 0xFFFF;
		byte value = mQueue.poll().byteValue();
		char ret = (value < 0) ? (char)(256 + value) : (char)(value);
		return ret;
	}
	
	public static void clearBuffer() {
		mQueue.clear();
	}
	
	public static boolean isConnected() {
		if (mSocket == null) return false;
		if (Build.VERSION.SDK_INT >= 14) {
			return mSocket.isConnected();
		}
		return true;
	}
	
	public static void close() {
		try {
			if (mSocket != null) {
				if (mInputStream != null) mInputStream.close();
				if (mOutputStream != null) mOutputStream.close();
				mSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mSocket = null;
		mInputStream = null;
		mOutputStream = null;
	}
}