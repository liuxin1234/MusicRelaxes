package com.liux.musicrelaxes.http;

import com.liux.module.BlueToothBean.ResultEntityDto;
import com.liux.module.BlueToothBean.heartRateRecord.SetMusicDto;
import com.liux.module.BlueToothBean.music.MusicListDto;
import com.liux.module.BlueToothBean.music.MusicParameterDto;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateParameterDto;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateRecordViewDto;
import com.liux.module.BlueToothBean.heartRateRecord.SendWeCardioDataDto;
import com.liux.module.BlueToothBean.student.ResetPwdDto;
import com.liux.module.BlueToothBean.student.StudentDto;
import com.liux.module.BlueToothBean.student.StudentLoginDto;
import com.liux.module.BlueToothBean.token.TokenBean;

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
     * 获取票据接口：
     * @param body:
     */
    @POST("token")
    Call<TokenBean> getTokenBean(@Body RequestBody body);

    /**
     * 用户登录接口：
     * @param studentLoginDto:
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/Student/login")
    Call<StudentDto> postStudentLogin(@Body StudentLoginDto studentLoginDto);


    /**
     * 用户忘记密码接口：
     * @param resetPwdDto
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/Student/resetpwd")
    Call<StudentDto> postStudentResetPWD(@Body ResetPwdDto resetPwdDto);


    /**
     * 音乐接口：
     * @param musicParameterDto
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/Music/getMusicByLFHF")
    Call<MusicListDto> postMusicByLFHF(@Body MusicParameterDto musicParameterDto);

    /**
     * 用户选择了某首音乐后，把音乐信息发送给服务器的接口：
     * @param setMusicDto
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/HeartRateRecord/SetMusic")
    Call<ResultEntityDto> postSetMusicData(@Body SetMusicDto setMusicDto);


    /**
     * 发送蓝牙心率分析结果的接口：
     * @param sendWeCardioDataDto
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/HeartRateRecord/SendWeCardioData")
    Call<ResultEntityDto> postSendWeCardioData(@Body SendWeCardioDataDto sendWeCardioDataDto);


    /**
     *获取当前心率记录的接口：
     * @param rateParameterDto
     * @return
     */
    @POST("api/Nbcei.Plugin.HealthCheck.Api.Impl/v1/HeartRateRecord/GetCurrentHeartRateRecord")
    Call<HeartRateRecordViewDto> postHeartRateRecord(@Body HeartRateParameterDto rateParameterDto);




    /**
     * 上传蓝牙心率数据接口
     * appId:应用id
     * userId:用户id
     * stamp:时间戳
     * sign:签名
     * @return
     * /company/upload?app_id={app_id}&user_id={user_id}&stamp={stamp}&sign={sign}
     */
    @POST("/company/upload")
    Call<String> postBlueToothFileData(@QueryMap Map<String, String> map, @Body RequestBody body);


    @POST("/company/add")
    Call<String> postAddRecord(@QueryMap Map<String, String> map, @Body RequestBody body);


}
