package com.liux.musicrelaxes.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.liux.module.BlueToothBean.heartRateRecord.HeartRateParameterDto;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateRecordViewDto;
import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.BlueToothConnectActivity;
import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.http.HttpManager;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.utils.GsonUtils;
import com.liux.musicrelaxes.view.ToolbarHelper;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by on
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2018/1/11
 * 作者：liux
 * 邮箱：750954283@qq.com
 * 公司：nbzhongjing
 * 功能： 目前没有用到的界面  （预留）
 * @author 75095
 */


public class MainActivity extends BaseSwipeBackActivity {


    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_jump_blueTooth)
    Button btnJumpBlueTooth;

    ACache mCache;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mCache = ACache.get(MRApplication.getContext());
        String studentId = mCache.getAsString("studentId");
        getCurrentHeartRateRecord(studentId);
    }


    /**
     * 获取当前学生测试状态数据
     * Status : 0   测试状态:  0、未测试,  1、报告已生成,  2、歌曲已分配, 3、测试已完成
     */
    private void getCurrentHeartRateRecord(String studentId){
        HeartRateParameterDto parameterDto = new HeartRateParameterDto();
        parameterDto.setStuId(studentId);
        GsonUtils.toJson(parameterDto);
        HttpManager.getInstance().getApiService().postHeartRateRecord(parameterDto).enqueue(new Callback<HeartRateRecordViewDto>() {
            @Override
            public void onResponse(@NonNull Call<HeartRateRecordViewDto> call, @NonNull Response<HeartRateRecordViewDto> response) {
                HeartRateRecordViewDto recordViewDto = response.body();
                if (recordViewDto != null && recordViewDto.isSuccess()){
                    HeartRateRecordViewDto.DataBean dataBean = recordViewDto.getData();
                    if (dataBean != null){
                        int status = dataBean.getStatus();
                        Logger.v(""+status);
                    }else {

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeartRateRecordViewDto> call, @NonNull Throwable t) {

            }
        });
    }



    @OnClick({R.id.btn_jump_blueTooth, R.id.btn_jump})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_jump_blueTooth:
                intent.setClass(MainActivity.this,BlueToothConnectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_jump:
                intent.setClass(MainActivity.this,ChooseMusicActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
