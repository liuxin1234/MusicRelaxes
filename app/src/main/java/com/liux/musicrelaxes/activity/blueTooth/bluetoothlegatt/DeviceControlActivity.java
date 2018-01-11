/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liux.musicrelaxes.activity.blueTooth.bluetoothlegatt;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.BlueToothWeCardioActivity;
import com.liux.musicrelaxes.eventBus.isFirstEvent;
import com.liux.musicrelaxes.utils.FileUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity {
    private final static String TAG = DeviceControlActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private TextView mConnectionState;
    private TextView mDataField;
    private String mDeviceName;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;

    private boolean mConnected = false;
    private BluetoothGattCharacteristic mNotifyCharacteristic;



    private ArrayList<String> arrayList = new ArrayList<>();
    private Handler handler;

    public static Integer mIntRate = 0;


    private SurfaceView sfv;
    /**
     * 绘制的数据
     */
    List<Integer> x = new ArrayList<>();

    Paint mPaint;

    /**
     * X轴缩小的比例
     */
    public int rateX = 352;

    /**
     * Y轴缩小的比例
     */
    public int rateY = 0;

    /**
     * Y轴基线
     */
    public int baseLine = 0;

    private final int TEXT_SIZE = 40;

    /**
     * 为了节约绘画时间，每三个像素画一个数据
     */
    int divider = 10;




    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };


    // Handles various events fired by the Service.处理服务所激发的各种事件
    // ACTION_GATT_CONNECTED: connected to a GATT server..连接一个GATT服务
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.从GATT服务中断开连接
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.查找GATT服务
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations..从服务中接受数据
    /**
     * 广播接收器 ,接受BluetoothLeService
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
            }
            //发现有可支持的服务
            else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
//                displayGattServices(mBluetoothLeService.getSupportedGattServices());
//                Log.e(TAG,"发现可用的服务");
                displayGattServicesSingle(mBluetoothLeService.getSupportedGattService(BluetoothLeService.UUID_HRP_SERVICE));
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
//                Log.e(TAG,"发现可用的数据");
                //这里接收的是解析成btye[]类型的数据，将数据显示在mDataField上
                mIntRate= Integer.valueOf(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_HEART_RATE));
                displayData(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_HEART_RATE));
                //这里接收的是解析成int类型的数据
//                initViews(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_HEART_RATE));
            }
        }


    };

    private void displayGattServicesSingle(BluetoothGattService supportedGattService) {
        final BluetoothGattCharacteristic characteristic = supportedGattService.getCharacteristic(BluetoothLeService.UUID_HEART_RATE_MEASUREMENT);
        final int charaProp = characteristic.getProperties();
//        Log.e(TAG,"onChildClick charaProp:"+charaProp);
//        Log.e(TAG,"onChildClick Uuid"+characteristic.getUuid());
        //如果是读属性，设置为通知属性不使能
        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
            // If there is an active notification on a characteristic, clear
            // it first so it doesn't update the data field on the user interface.
            if (mNotifyCharacteristic != null) {
                //设置为通知属性不使能
                mBluetoothLeService.setCharacteristicNotification(
                        mNotifyCharacteristic, false);
                mNotifyCharacteristic = null;
            }
//            Log.e(TAG,"onChildClick 调用读方法读特性:");
            //调用读方法读特性readCharacteristic()，触发onCharacteristicGhanged()
            mBluetoothLeService.readCharacteristic(characteristic);
        }

        //如果是通知属性，设置为通知属性使能
        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
            mNotifyCharacteristic = characteristic;
//            Log.e(TAG,"onChildClick 启动了属性通知:");
            //启动了属性通知
            mBluetoothLeService.setCharacteristicNotification(
                    characteristic, true);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new isFirstEvent(true,charaProp,characteristic));
            }
        },2000);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        setContentView(R.layout.gatt_services_characteristics);

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        // Sets up UI references.
        ((TextView) findViewById(R.id.device_address)).setText(mDeviceAddress);

        mConnectionState = (TextView) findViewById(R.id.connection_state);
        mDataField = (TextView) findViewById(R.id.data_value);


        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);

        initViews();

        //这里我用了handler来做一个延迟1分钟后的操作,这里如果用户提前关闭了当前的Activity界面的话，程序还是会执行，原因是handler是异步线程，并没有关闭
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.e(arrayList.toString());
                FileUtil.getString(arrayList.toString());
                //注意这里如果断开了蓝牙连接，此处的mBluetoothLeService会变为空出现异常，导致APP闪退
                if (mBluetoothLeService == null){
                    Log.e(TAG,"蓝牙已断开,请重新连接！！！");
                    Toast.makeText(DeviceControlActivity.this, "蓝牙已断开,请重新连接！！！", Toast.LENGTH_SHORT).show();
                }else {
                    mBluetoothLeService.disconnect(); //断开当前蓝牙的连接

                    Intent intent1 = new Intent(DeviceControlActivity.this, BlueToothWeCardioActivity.class);
                    intent1.putStringArrayListExtra("blueToothData",arrayList);
                    Logger.e(arrayList.toString());
                    startActivity(intent1);
                    finish();
                }
            }
        },60*1000);

    }



    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }





    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConnectionState.setText(resourceId);
            }
        });
    }

    private void displayData(String data) {
        if (data != null) {
            arrayList.add(data);
            mDataField.setText(data);
        }
    }

    /**
     * 该方法是为了测试直接连接到蓝牙设备，不需要手动去连接
     * 因为这里需要请求2次，
     * 第一次为连接对应的蓝牙接口serviced的UUID，
     * 第二次获取蓝牙设备characteristic传递过来的数据
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(isFirstEvent firstEvent){
        boolean isfirst = firstEvent.isfirst();
        if (isfirst){
            Log.e(TAG,"isfirst: "+isfirst);
            int charaProp = firstEvent.getCharaProp();
            BluetoothGattCharacteristic characteristic = firstEvent.getCharacteristic();
            //如果是读属性，设置为通知属性不使能
            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                // If there is an active notification on a characteristic, clear
                // it first so it doesn't update the data field on the user interface.
                if (mNotifyCharacteristic != null) {
                    //设置为通知属性不使能
                    mBluetoothLeService.setCharacteristicNotification(
                            mNotifyCharacteristic, false);
                    mNotifyCharacteristic = null;
                }
                Log.e(TAG,"onChildClick Uuid"+characteristic.getUuid());
                Log.e(TAG,"调用读方法读特性readCharacteristic()");
                //调用读方法读特性readCharacteristic()，触发onCharacteristicGhanged()
                mBluetoothLeService.readCharacteristic(characteristic);
            }

            //如果是通知属性，设置为通知属性使能
            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                mNotifyCharacteristic = characteristic;
                Log.e(TAG,"动了属性通知");
                //启动了属性通知
                mBluetoothLeService.setCharacteristicNotification(
                        characteristic, true);
            }
        }
    }

    /**
     * 蓝牙控制DeviceControlActivity
     * 扫描到蓝牙设备之后就是对蓝牙进行自己需要的控制，比如写数据，读数据，获取设备信息，设备电量等。
     * 在Service中讲到有一个广播，广播接收器就在这个activity中，通过不同的action做出相应的操作。
     * 注册的几种事件
     * @return
     */
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }



    AsyncTask<Integer,Integer,Integer> asyncTask;
    private void initViews() {
        sfv = (SurfaceView) findViewById(R.id.electrocardiogram_surfaceView);
        sfv.setZOrderOnTop(true); //把SurfaceView置于Activity显示窗口的最顶层才能正常显示
        sfv.getHolder().setFormat(PixelFormat.TRANSLUCENT); //系统选用的格式，支持半透明
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);// 画笔为主色调
        mPaint.setStrokeWidth(5);// 设置画笔粗细
        baseLine = sfv.getHeight() / 2;
        doAsyncTask();
    }

    public void doAsyncTask(){
        asyncTask =  new AsyncTask<Integer,Integer,Integer>(){
            @Override
            protected Integer doInBackground(Integer... integers) {
                SystemClock.sleep(300);
                while(!this.isCancelled()){
                    x.add(mIntRate); //意思随机产生1-121的数字
                    publishProgress(1); // 通过publishProgress()发消息传递给onProgressUpdate()一般用来更新界面
                    SystemClock.sleep(200);
                }
                return 0;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                try {
//                    Log.e("x的数据大小：",""+x.size()+"\n"+x.toString());
                    //如果算出的来数据,大于宽度的话,那么则减去一个数值
                    if (x.size() > sfv.getWidth() / divider) {
                        x.remove(0);
                    }
                    if (rateY == 0) {
                        rateY = 2;
                        baseLine = sfv.getHeight() / 2;
                    }
                    //获取到画布
                    Canvas canvas = sfv.getHolder().lockCanvas(
                            new Rect(0, 0, sfv.getWidth(), sfv.getHeight()));// 关键:获取画布
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// 清除背景 设置为透明
                    mPaint.setStrokeWidth(5);// 设置画笔粗细
                    //开始的位置
                    int start = 0;
                    int py = baseLine;
                    if (x.size() > 0) {
                        Log.d(TAG, "onProgressUpdate: "+x.get(0));
                        py += x.get(0) / rateY ;
                    }
                    int y;
                    //绘制数据
                    for (int i = 0; i < x.size(); i++) {
                        y = baseLine - x.get(i)  ;
                        canvas.drawLine(start + (i - 1) * divider - TEXT_SIZE, py, start + i * divider - TEXT_SIZE,
                                y, mPaint);
                        int i1 = start + (i - 1) * divider - TEXT_SIZE;
                        int i2 = start + i * divider - TEXT_SIZE;
                        Log.d(TAG, "onProgressUpdate: "+"("+i1+","+py+")" + "-/-"+"("+ i2 +","+ y +")" +"\n");
                        py = y;

                    }
                    mPaint.setStrokeWidth(0);// 设置画笔粗细

                    mPaint.setTextSize(20); //设置字体大小，int型，如12
                    canvas.drawText(""+x.get(x.size() - 1), start + (x.size() - 1 ) * divider - TEXT_SIZE , py + TEXT_SIZE, mPaint);
                    sfv.getHolder().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
                }catch (Exception e){

                }
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();

            }
        }.execute();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
        //反注册EventBus
        EventBus.getDefault().unregister(this);
        //将异步线程关闭
        if (asyncTask != null && !asyncTask.isCancelled() &&
                asyncTask.getStatus() == AsyncTask.Status.RUNNING){
            asyncTask.cancel(true);
            Log.d(TAG, "onDestroy: "+asyncTask.isCancelled());
            asyncTask = null;
        }
        SystemClock.sleep(100);
        mIntRate = 0;
    }

}
