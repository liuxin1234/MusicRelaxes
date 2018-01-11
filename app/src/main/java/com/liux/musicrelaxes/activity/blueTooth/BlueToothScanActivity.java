package com.liux.musicrelaxes.activity.blueTooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.bluetoothlegatt.DeviceControlActivity;
import com.liux.musicrelaxes.adapter.LeDeviceListAdapter;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlueToothScanActivity extends AppCompatActivity {

    public static final int REQUEST_OPEN_BT = 0x01; //打开蓝牙
    @BindView(R.id.btn_jump_new_activity)
    Button btnJumpNewActivity;
    private Button btnOpenBT;
    private Button btnFindDevice;
    private TextView tvInfo;
    private TextView tvFindDevice;
    private BluetoothAdapter mBluetoothAdapter;

    private Handler mHandler;
    private boolean mScanning;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<>();
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private ListView mListView;


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            String action = intent.getAction();
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 搜索到的不是已经绑定的蓝牙设备
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    // 显示在TextView上
                    tvFindDevice.append(device.getName() + ":"
                            + device.getAddress() + "\n");
                }
                // 搜索完成
            } else if (action
                    .equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle("搜索蓝牙设备");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_demo);
        ButterKnife.bind(this);

        mHandler = new Handler();

        //获取本地蓝牙的适配器
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //判断蓝牙功能是否存在
        if (mBluetoothAdapter == null) {
            showToast("该设备不支持蓝牙功能....");
            return;
        }

        tvInfo = (TextView) findViewById(R.id.tv_Info);
        btnOpenBT = (Button) findViewById(R.id.btn_Open_BlueTooth);
        tvFindDevice = (TextView) findViewById(R.id.tv_Find_Device_Info);
        btnFindDevice = (Button) findViewById(R.id.btn_Find_Device);
        btnOpenBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭---打开本地蓝牙设备
                //判断蓝牙是否已经打开了
                if (mBluetoothAdapter.isEnabled()) {
                    showToast("蓝牙已经处于打开状态....");
//                    mBluetoothAdapter.disable(); //关闭蓝牙
                } else {

                    //调用系统API打开
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_OPEN_BT);

                }
            }
        });
        btnFindDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanLeDevice(true);

//                getBlueToothDevice();
            }
        });

        setMyDeviceInfo();


        mListView = (ListView) findViewById(R.id.list_view);

        mLeDeviceListAdapter = new LeDeviceListAdapter(this);
        mListView.setAdapter(mLeDeviceListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);
                if (device == null) {
                    return;
                }
                final Intent intent = new Intent(BlueToothScanActivity.this, DeviceControlActivity.class);
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                startActivity(intent);
            }
        });


    }

    private void getBlueToothDevice() {
        // 获取所有已经绑定的蓝牙设备
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        if (devices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : devices) {
                tvFindDevice.append(bluetoothDevice.getName() + ":"
                        + bluetoothDevice.getAddress() + "\n\n");
            }
        }
        // 注册用以接收到已搜索到的蓝牙设备的receiver
        IntentFilter mFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, mFilter);
        // 注册搜索完时的receiver
        mFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, mFilter);
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
                    invalidateOptionsMenu();

                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLeDeviceListAdapter.addDevice(device);
                            mLeDeviceListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };

    private void setMyDeviceInfo() {
        //获取名字 mac地址
        String name = mBluetoothAdapter.getName();
        @SuppressLint("HardwareIds")
        String mac = mBluetoothAdapter.getAddress();

        tvInfo.setText(name + "\n" + mac);
    }

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
                        showToast("蓝牙已经打开");
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

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_jump_new_activity)
    public void onViewClicked() {
        Intent intent = new Intent(this, BlueToothWeCardioActivity.class);
        startActivity(intent);
    }
}
