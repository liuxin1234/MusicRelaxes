package com.liux.musicrelaxes.http;

import android.util.Log;


import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.http.Api.ApiConstants;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.utils.NetUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目：OkHttp_Retrofit
 * 作者：nbcei
 * 时间：2017/7/5
 * 功能：实现网络请求
 */

public class HttpManager {
    private static final String TAG = "HttpUtil";

    private Retrofit mRetrofit;
    private RetrofitService apiService;
    private static HttpManager instance;
    private OkHttpClient mOkHttpClient;

    private String taken;


    /**
     * 存储票据的工具类
     */
    private ACache mCache;

    /***
     * 获取单例
     */
    public static HttpManager getInstance(){

        if (instance == null){
            synchronized (HttpManager.class){
                if (instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private HttpManager() {

    }


    /***
     * 判断网络状态
     * @return boolean
     */
    public boolean isNetState(){
        int netWorkState = NetUtil.getNetWorkState(MRApplication.getContext());
        Log.e("main", "isNetState: " );
        //netWorkStat-1,0,1 分别代表无无网络状态，移动网络，wifi网络
        return netWorkState != -1;

    }

    public RetrofitService getApiService(){

        mCache = ACache.get(MRApplication.getContext());
        taken=mCache.getAsString("access_token");

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        //网络连接
                        final Request request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + taken)
                                .addHeader("Content-Type", "application/json")
                                .build();
                        Response response = chain.proceed(request);
                        //打印服务器返回的数据,可以打印出数据
                        MediaType mediaType = response.body().contentType();
                        String content = response.body().string();

//                        String method = request.method();
//                        logPostFormBody(request,method);
                        Logger.v(request.url().toString() +"\n"+ request.method()
                                + "\n"+ request.body() + "\n" + request.headers()); //打印的是API地址
                        Logger.json(content);   //打印的是JSON数据

                        return response.newBuilder()
                                .body(ResponseBody.create(mediaType, content))
                                .build();

                    }
                }).build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.IP_NB_CEI).client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        apiService = mRetrofit.create(RetrofitService.class);


        return apiService;
    }


    /**
     * 打印提交表单的参数
     * @param request
     * @param method
     */
    private void logPostFormBody(Request request,String method){
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Logger.e("| RequestParams:{"+sb.toString()+"}");
            }
        }


    }

}
