package com.liux.musicrelaxes.eventBus;


import android.bluetooth.BluetoothGattCharacteristic;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.eventBus
 * 项目日期：2017/12/22
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class isFirstEvent {
    private boolean isfirst;
    private int charaProp;
    private BluetoothGattCharacteristic characteristic;

    public isFirstEvent(boolean isfirst, int charaProp, BluetoothGattCharacteristic characteristic) {
        this.isfirst = isfirst;
        this.charaProp = charaProp;
        this.characteristic = characteristic;
    }

    public boolean isfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public int getCharaProp() {
        return charaProp;
    }

    public void setCharaProp(int charaProp) {
        this.charaProp = charaProp;
    }

    public BluetoothGattCharacteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(BluetoothGattCharacteristic characteristic) {
        this.characteristic = characteristic;
    }
}
