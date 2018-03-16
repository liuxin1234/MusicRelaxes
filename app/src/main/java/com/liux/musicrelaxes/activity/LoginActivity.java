package com.liux.musicrelaxes.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liux.module.BlueToothBean.heartRateRecord.HeartRateParameterDto;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateRecordViewDto;
import com.liux.module.BlueToothBean.student.StudentDto;
import com.liux.module.BlueToothBean.student.StudentLoginDto;
import com.liux.module.BlueToothBean.token.TokenBean;
import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.blueTooth.BlueToothConnectActivity;
import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.http.HttpManager;
import com.liux.musicrelaxes.http.TokenManager;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.utils.EncryptUtil;
import com.liux.musicrelaxes.utils.GsonUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2017/12/26
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.layout_login_btn)
    LinearLayout layoutLogin;

    Editable etUserNameText;
    Editable etPassWordText;

    /***
     * 票据
     */
    private String accessToken =null;
    /**
     * 存储票据的工具类
     */
    private ACache mCache;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acitvity_login);
        ButterKnife.bind(this);
        mCache = ACache.get(MRApplication.getContext());

        initView();
        initLoginInfo();
        mDialog = new ProgressDialog(this);
    }


    private void initLoginInfo(){
        //读取登录成功后保存的用户名、密码和appkey
        String localId = mCache.getAsString(USERNAME);
        if (!TextUtils.isEmpty(localId)) {
            etUserName.setText(localId);
            String localPassword = mCache.getAsString(PASSWORD);
            if (!TextUtils.isEmpty(localPassword)) {
                etPassWord.setText(localPassword);
            }
        }
    }


    private void initView() {
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    etPassWord.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 登录
     */
    private void login(){
        //当用户点击了登录按钮后让按钮失效，这样防止用户误触多次登录
//        layoutLogin.setClickable(false);
         etUserNameText = etUserName.getText();
         etPassWordText = etPassWord.getText();


        if (etUserNameText ==null || etUserNameText.toString().length() == 0){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//            layoutLogin.setEnabled(true);
            return;
        }

        if (etPassWordText == null || etPassWordText.toString().length() == 0){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
//            layoutLogin.setEnabled(true);
            return;
        }

        getTakenNet();

    }

    /**
     * 获取票据
     */
    private void getTakenNet() {
        showNormalDialog("正在登录，请稍候.....");
        //发送请求，获取票据，并保存到本地
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("grant_type", "client_credentials");

        RequestBody body = builder.build();

        TokenManager.getInstance().getApiService().getTokenBean(body).enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(@NonNull Call<TokenBean> call, @NonNull Response<TokenBean> response) {
                TokenBean tokenBean = response.body();
                if (tokenBean!=null){
                    accessToken  = tokenBean.getAccess_token();
                    final String clientId = tokenBean.getUserId();

                    if (accessToken !=null){
                        mCache.put("access_token", accessToken  , ACache.TIME_DAY);//保存1天，如果1过两天去获取这个key，将为null
                        loginStudent();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "获取票据失败", Toast.LENGTH_SHORT).show();
                    dissMissNormalDialog();
                }
                layoutLogin.setClickable(true);
            }

            @Override
            public void onFailure(@NonNull Call<TokenBean> call, @NonNull Throwable t) {
                dissMissNormalDialog();
                Toast.makeText(LoginActivity.this, "获取票据失败", Toast.LENGTH_SHORT).show();
                layoutLogin.setClickable(true);
            }
        });

    }


    private void loginStudent(){

        StudentLoginDto studentLoginDto = new StudentLoginDto();
        studentLoginDto.setLoginUserName(etUserName.getText().toString());
        studentLoginDto.setLoginPassword(EncryptUtil.makeMD5(etPassWord.getText().toString()));
        GsonUtils.toJson(studentLoginDto);

        HttpManager.getInstance().getApiService().postStudentLogin(studentLoginDto).enqueue(new Callback<StudentDto>() {
            @Override
            public void onResponse(@NonNull Call<StudentDto> call, @NonNull Response<StudentDto> response) {
                StudentDto body = response.body();
                if (body != null && body.isSuccess()){
                        StudentDto.DataBean dataBean = body.getData();
                        if (dataBean !=null){
                            saveLoginInfoToLocal(etUserNameText.toString(),etPassWordText.toString());

                            String studentId = dataBean.getId();
                            mCache.put("studentId",studentId);

                            getCurrentHeartRateRecord(studentId);

                        }else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                            dissMissNormalDialog();
                        }
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    dissMissNormalDialog();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StudentDto> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                dissMissNormalDialog();
            }
        });
    }

    /**
     * 保存登录的用户名密码到本地
     *
     */
    private void saveLoginInfoToLocal(String userName, String passWord) {
        mCache.put(USERNAME,userName);
        mCache.put(PASSWORD,passWord);
    }


    /**
     * 获取当前学生测试状态数据
     * Status : 0   测试状态:  0、未测试,  1、报告已生成,  2、歌曲已分配, 3、测试已完成
     * @param studentId
     */
    private void getCurrentHeartRateRecord(String studentId){
        HeartRateParameterDto parameterDto = new HeartRateParameterDto();
        parameterDto.setStuId(studentId);
        GsonUtils.toJson(parameterDto);
        HttpManager.getInstance().getApiService().postHeartRateRecord(parameterDto).enqueue(new Callback<HeartRateRecordViewDto>() {
            @Override
            public void onResponse(@NonNull Call<HeartRateRecordViewDto> call, @NonNull Response<HeartRateRecordViewDto> response) {
                dissMissNormalDialog();
                HeartRateRecordViewDto recordViewDto = response.body();
                if (recordViewDto != null && recordViewDto.isSuccess()){
                    HeartRateRecordViewDto.DataBean dataBean = recordViewDto.getData();
                    if (dataBean != null){
                        mCache.put("HeartRateRecordDataBean",dataBean);
                        int status = dataBean.getStatus();
                        stateJumpActivity(status);
                    }else {
                        Intent intent = new Intent(LoginActivity.this,StateNullActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeartRateRecordViewDto> call, @NonNull Throwable t) {
                dissMissNormalDialog();
                Logger.e("onFailure: "+t.getMessage());
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
            //3、测试已完成
            intent.setClass(this,StateNullActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @OnClick(R.id.layout_login_btn)
    public void onViewClicked() {
        login();
    }



    /**
     * 显示提示框
     * @param msg：文字提示
     */
    public void showNormalDialog(String msg){
        mDialog.setMessage(msg);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.show();
    }

    /**
     * 提示框消失
     */
    public void dissMissNormalDialog(){
        try{
            if (mDialog.isShowing() && mDialog !=null){
                mDialog.dismiss();
            }
        }catch (Exception ignored){
            ignored.printStackTrace();
        }
    }
}
