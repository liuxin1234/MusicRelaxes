package com.liux.musicrelaxes.activity.blueTooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.bluetoothlegatt.DeviceControlActivity;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.view.ToolbarHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity.blueTooth
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：自动连接蓝牙心率设备界面
 *
 * @author 750954283(qq)
 */

public class BlueToothConnectActivity extends BaseSwipeBackActivity {

    public static final int REQUEST_OPEN_BT = 0x01; //打开蓝牙
    public static final String DEVICE_NAME = "WeCardio STD"; //蓝牙设备名称

    @BindView(R.id.btn_jump_blueTooth)
    Button btnJumpBlueTooth;

    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler;
    private boolean mScanning;
    // 20秒后停止扫描。
    private static final long SCAN_PERIOD = 20000;
    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<>();


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_blue_tooth_connect;
    }

    @Override
    protected void initView() {
        mHandler = new Handler();
        //获取本地蓝牙的适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    private void openBlueTooth() {
        //关闭---打开本地蓝牙设备
        //判断蓝牙是否已经打开了
        if (mBluetoothAdapter.isEnabled()) {
            showToast("蓝牙已经处于打开状态....");
            scanLeDevice(true);
        } else {
            //调用系统API打开
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_OPEN_BT);
        }
    }

    /**
     * 搜索周围的蓝牙设备
     *
     * @param enable
     */
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("tag:-----", "scanLeDevice");
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    mLeDevices.clear(); //20秒后停止扫描蓝牙设备，并且把之前扫描添加到数组里的蓝牙设备号数据清空，防止下次扫描，就算是扫描到设备也无法跳转界面的bug.

                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //mLeDevices.contains(device)如果是同一对象即地址相同的情况下，才会返回true，
                            // 而对于对象属性值相同但地址不同的不同对象，始终返回false！所以这就是我们要的，将不同的进行添加到list中
                            if (!mLeDevices.contains(device)) {
                                mLeDevices.add(device);
                                if (DEVICE_NAME.equals(device.getName())) {
                                    showToast("连接到了心率蓝牙设备");
                                    final Intent intent = new Intent(BlueToothConnectActivity.this, DeviceControlActivity.class);
                                    intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
                                    intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                                    startActivity(intent);

                                }
                            }


                        }
                    });
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OPEN_BT) {
            if (resultCode == RESULT_CANCELED) {
                showToast("请求失败。。。");
            } else {
                showToast("请求成功。。。");
                //获取名字 mac地址
                String name = mBluetoothAdapter.getName();
                @SuppressLint("HardwareIds")
                String mac = mBluetoothAdapter.getAddress();
                //获取当前蓝牙的状态
                int state = mBluetoothAdapter.getState();
                switch (state) {
                    case BluetoothAdapter.STATE_ON:
                        //蓝牙已经打开
                        scanLeDevice(true);
                        showToast("蓝牙已经打开,请按文字提示连接心率设备。");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        //蓝牙正在打开
                        showToast("蓝牙正在打开");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        //蓝牙正在关闭
                        showToast("蓝牙正在关闭");
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        //蓝牙已经关闭
                        showToast("蓝牙已经关闭");
                        break;
                    default:
                        break;
                }

            }
        }
    }


    @OnClick(R.id.btn_jump_blueTooth)
    public void onViewClicked() {
        //判断蓝牙功能是否存在
        if (mBluetoothAdapter == null) {
            showToast("该设备不支持蓝牙功能....");
            return;
        }
        openBlueTooth();
    }
}
