package com.liux.musicrelaxes.http;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.http
 * 项目日期：2018/1/3
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public interface RetrofitService {

    /**
     * 上传蓝牙心率数据接口
     * @param appId:应用id
     * @param userId:用户id
     * @param stamp:时间戳
     * @param sign:签名
     * @return
     * /company/upload?app_id={app_id}&user_id={user_id}&stamp={stamp}&sign={sign}
     */
    @POST("/company/upload")
    Call<String> postBlueToothFileData(@QueryMap Map<String, String> map, @Body RequestBody body);


    @POST("/company/add")
    Call<String> postAddRecord(@QueryMap Map<String, String> map, @Body RequestBody body);

}
