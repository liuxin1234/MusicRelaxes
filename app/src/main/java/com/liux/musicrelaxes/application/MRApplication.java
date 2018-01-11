package com.liux.musicrelaxes.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by
 * 项目名称：com.liux.easyreading.application
 * 项目日期：2017/11/2
 * 作者：liux
 * 功能：
 * @author 75095
 */

public class MRApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //logger2.1.1版本必需添加的适配器，否则打印不出日志信息，设置在这里供整个APP调用
        Logger.addLogAdapter(new AndroidLogAdapter());


    }

    public static Context getContext(){
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 使其系统更改字体大小无效,若用户将字体改大这里强制该成默认的标准字体
     * 注意：这里网上有人说联想手机不行，还有7.0中设置手机系统字体方法过时了不可用
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
        {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }


}
