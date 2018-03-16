package com.liux.musicrelaxes.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
 * 功能： data 数据为null 的时候展示该界面
 * @author 75095
 */


public class StateNullActivity extends BaseSwipeBackActivity {


    @BindView(R.id.btn_jump_event)
    Button btnJumpEvent;

    ACache mACache;

    String studentId;



    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_state_null;
    }

    @Override
    protected void initView() {
        mACache = ACache.get(MRApplication.getContext());
        studentId = mACache.getAsString("studentId");

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
                        mACache.put("HeartRateRecordDataBean",dataBean);
                        int status = dataBean.getStatus();
                        stateJumpActivity(status);
                    }else {
                        Toast.makeText(StateNullActivity.this, "你还未分配测试记录,请老师分配。", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeartRateRecordViewDto> call, @NonNull Throwable t) {

            }
        });
    }

    /**
     * 根据状态来进行跳转不同界面
     * @param state：
     */
    private void stateJumpActivity(int state){
        Intent intent = new Intent();
        if (state == 0){
            //0、未测试
            intent.setClass(this,BlueToothConnectActivity.class);
            startActivity(intent);
            finish();
        }

        if (state == 1){
            //1、报告已生成
            intent.setClass(this,ChooseMusicActivity.class);
            startActivity(intent);
            finish();
        }

        if (state == 2){
            //2、歌曲已分配
            intent.setClass(this,PlayMusicActivity.class);
            startActivity(intent);
            finish();
        }

        if (state == 3){
            Toast.makeText(this, "你还未分配测试记录,请老师分配。", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_jump_event)
    public void onViewClicked(View view) {
        if (studentId != null){
            getCurrentHeartRateRecord(studentId);
        }
    }
}
