package com.liux.musicrelaxes.http;

import android.util.SparseArray;


import com.liux.musicrelaxes.http.Api.ApiConstants;
import com.liux.musicrelaxes.http.Api.IpType;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.http
 * 项目日期：2018/1/3
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class RetrofitManager {

    private static final int TIME_OUT = 10;
    private RetrofitService mRetrofitService;
    private static volatile OkHttpClient okHttpClient;
    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>();


    public static RetrofitManager getInstance(int ipTyep){
        RetrofitManager retrofitManager = sRetrofitManager.get(ipTyep);
        if (retrofitManager == null){
            retrofitManager = new RetrofitManager(ipTyep);
            sRetrofitManager.put(ipTyep,retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    public RetrofitService getRetrofitService(){
        return mRetrofitService;
    }


    public RetrofitManager(@IpType.IPTypeChecker int ipType){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getIP(ipType))
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mRetrofitService = retrofit.create(RetrofitService.class);
    }

    public OkHttpClient getOkHttpClient(){
        if (okHttpClient == null){
            synchronized (RetrofitManager.class){
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                            .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                            .addInterceptor(loggingInterceptor)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 请求的日志拦截器
     */
    private final Interceptor loggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Logger.e(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

}
