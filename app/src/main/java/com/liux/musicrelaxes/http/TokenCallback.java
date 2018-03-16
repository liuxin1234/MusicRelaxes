package com.liux.musicrelaxes.http;

/**
 * Created by
 * 项目名称：com.example.administrator.mobileoa_android.common
 * 项目日期：2017/10/13
 * 作者：liux
 * 功能：只要用于票据过期后，重新请求获取票据的接口 回调判断
 */

public interface TokenCallback {
    void onSuccess();

    void onFail();
}
