package com.liux.musicrelaxes.activity.blueTooth;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.liux.module.BlueToothBean.AddRecordBean;
import com.liux.module.BlueToothBean.UpLoadFileBean;
import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.common.CommonInfo;
import com.liux.musicrelaxes.http.Api.IpType;
import com.liux.musicrelaxes.http.RetrofitManager;
import com.liux.musicrelaxes.utils.DateUtils;
import com.liux.musicrelaxes.utils.EncryptUtil;
import com.liux.musicrelaxes.utils.FileUtil;
import com.liux.musicrelaxes.utils.GsonUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.activity
 * 项目日期：2018/1/4
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class BlueToothWeCardioActivity extends AppCompatActivity {


    @BindView(R.id.btn_UpData)
    Button btnUpData;
    @BindView(R.id.btn_Add)
    Button btnAdd;
    @BindView(R.id.tv_response)
    TextView tvResponse;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    private String file_no;

    private String sBluetoothData;
    private  String readString;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_we_cardio);
        ButterKnife.bind(this);
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator +"hello.txt";
        readString = FileUtil.readString(filePath, "utf-8");
        ArrayList<String> blueToothData = getIntent().getStringArrayListExtra("BlueToothData");

        if (blueToothData != null && blueToothData.size() > 0){
            sBluetoothData = blueToothData.toString();
            Logger.e(sBluetoothData);
        }


    }


    public void upData() {
        String currentTime = DateUtils.getCurrentTimeToday(); //获取当前时间
        String dataOne = DateUtils.dataOne(currentTime); //当前时间转化成时间戳 10位

        TreeMap<String,String> map = new TreeMap<>();
        map.put("app_id","11002");
        map.put("user_id","188");
        map.put("stamp",dataOne);
        GsonUtils.toJson(map);

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("app_id","11002");
        hashMap.put("user_id","188");
        hashMap.put("stamp",dataOne);
        hashMap.put("sign",signMD5(map));
        GsonUtils.toJson(hashMap);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), readString.getBytes());

        RetrofitManager.getInstance(IpType.BLUE_TOOTH_IP).getRetrofitService()
                .postBlueToothFileData(hashMap,requestFile).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    String toothBean = response.body();
                    if (toothBean !=null){
                        UpLoadFileBean upLoadFileBean = GsonUtils.stringToObject(toothBean, UpLoadFileBean.class);
                        String data = upLoadFileBean.getData();
                        AddRecordBean addRecordBean = GsonUtils.stringToObject(data, AddRecordBean.class);
                        file_no = addRecordBean.getFile_no();
                        Logger.e(file_no);
                        tvResponse.setText(toothBean);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Logger.e(t.toString());
            }
        });

    }

    private String signMD5(TreeMap<String, String> map) {
        String value = "";
        for (String key : map.keySet()) {
            value+= map.get(key);
        }
        Logger.e(value);
        String signMD5 = EncryptUtil.makeMD5(value+ CommonInfo.token+CommonInfo.companyKey);
        return signMD5;
    }

    private void addData(){
        String currentTime = DateUtils.getCurrentTimeToday(); //获取当前时间
        String dataOne = DateUtils.dataOne(currentTime); //当前时间转化成时间戳 10位

        HashMap<String,String> requestMap = new HashMap<>();
        requestMap.put("file_no",file_no);
        requestMap.put("test_time",dataOne);
        requestMap.put("type","5");
        requestMap.put("condition","有点累!!!");
        String toJson = GsonUtils.toJson(requestMap);
        Logger.e(toJson);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),toJson);


        TreeMap<String,String> map = new TreeMap<>();
        map.put("app_id","11002");
        map.put("user_id","188");
        map.put("stamp",dataOne);
        map.put("file_no",file_no);
        map.put("test_time",dataOne);
        map.put("type","5");
        map.put("condition","有点累!!!");
        GsonUtils.toJson(map);

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("app_id","11002");
        hashMap.put("user_id","188");
        hashMap.put("stamp",dataOne);
        hashMap.put("sign",signMD5(map));
        GsonUtils.toJson(hashMap);


        RetrofitManager.getInstance(IpType.BLUE_TOOTH_IP).getRetrofitService()
                .postAddRecord(hashMap,requestBody1).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    String body = response.body();
                    if (body != null){
                        Logger.e(body);
                        tvAdd.setText(body);
                    }

            }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Logger.e(t.getMessage());
            }
        });

    }

    @OnClick({R.id.btn_UpData, R.id.btn_Add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_UpData:
                upData();
                break;
            case R.id.btn_Add:
                addData();
                break;
            default:
                break;
        }
    }
}
