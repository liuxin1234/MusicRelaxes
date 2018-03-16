package com.liux.musicrelaxes.http;

import android.text.TextUtils;
import android.util.Log;


import com.liux.module.BlueToothBean.token.TokenBean;
import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.http.Api.ApiConstants;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.utils.EncryptUtil;
import com.liux.musicrelaxes.utils.NetUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 项目：OkHttp_Retrofit
 * 作者：nbcei
 * 时间：2017/7/4
 * 功能：获取票据，判断票据是否过时，不过时从本地获取票据，过时从新向服务区获取票据，
 */

public class TokenManager {
    private static final String TAG = "TakenUtil";

    private Retrofit mRetrofit;

    /***
     * 票据
     */
    private String access_token=null;


    /**
     * 存储票据的工具类
     */
    private ACache mCache;


    private RetrofitService apiService;
    private static TokenManager instance;



    /***
     * 在构造方法中okHttpClient,retrofit
     */
    private TokenManager() {
        //生成okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request request = chain.request().newBuilder().header("Authorization", "Basic " + ApiConstants.BASIC)
                                .addHeader("Content-Type", "application/json;charset=UTF-8")
                                .addHeader("Connection", "keep-alive")
                                .build();
                        Response response = chain.proceed(request);
                        //打印服务器返回的数据,可以打印出数据
                        MediaType mediaType = response.body().contentType();
                        String content = response.body().string();
//                        Logger.e(content);
                        Logger.e(""+request.url());
                        Logger.json(content);
                        return response.newBuilder()
                                .body(ResponseBody.create(mediaType, content))
                                .build();
                    }
                }).build();


        mRetrofit=new Retrofit.Builder()
                .baseUrl(ApiConstants.IP_NB_CEI).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        apiService = mRetrofit.create(RetrofitService.class);

        mCache = ACache.get(MRApplication.getContext());

    }


    /***
     * 获取单例
     * @return
     */
    public static TokenManager getInstance(){

        if (instance == null){
            synchronized (TokenManager.class){
                if (instance == null){
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }


    /***
     * 判断网络状态
     * @return
     */
    public boolean isNetState(){
        int netWorkState = NetUtil.getNetWorkState(MRApplication.getContext());
        Log.e("main", "isNetState: " );
        //netWorkStat-1,0,1 风别代表无无网络状态，移动网络，wifi网络
        return netWorkState != -1;

    }



    /***
     * 当票据不存在当时候重新获取一次票据
     * @return
     */
    public void getTakenNet(String username, String password, final TokenCallback takenCallbakc) {

            //发送请求，获取票据，并保存到本地
            FormBody.Builder builder = new FormBody.Builder();
            if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                builder.add("grant_type", "password");
                builder.add("username", username);
                builder.add("password", EncryptUtil.makeMD5(password));

            }

            RequestBody body = builder.build();

            apiService.getTokenBean(body).enqueue(new Callback<TokenBean>() {
                @Override
                public void onResponse(Call<TokenBean> call, retrofit2.Response<TokenBean> response) {
                    TokenBean tokenBean = response.body();
                    if (tokenBean!=null){
                        access_token = tokenBean.getAccess_token();
                        if (access_token!=null){
                            mCache.put("access_token", access_token , ACache.TIME_DAY);//保存1天，如果过期了去获取这个key，将为null
                            mCache.put("client_id",tokenBean.getUserId());

                            takenCallbakc.onSuccess();
                        }
                    }

                }

                @Override
                public void onFailure(Call<TokenBean> call, Throwable t) {

                    takenCallbakc.onFail();
                }
            });
    }


    public RetrofitService getApiService(){
        return apiService;
    }


}
