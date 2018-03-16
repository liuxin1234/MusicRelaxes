package com.liux.musicrelaxes.activity.blueTooth;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.liux.module.BlueToothBean.ResultEntityDto;
import com.liux.module.BlueToothBean.blueTooth.addRecord.AddRecordDataBean;
import com.liux.module.BlueToothBean.blueTooth.upLoadFile.UpLoadDataBean;
import com.liux.module.BlueToothBean.blueTooth.upLoadFile.UpLoadFileBean;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateRecordViewDto;
import com.liux.module.BlueToothBean.heartRateRecord.SendWeCardioDataDto;
import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.activity.ChooseMusicActivity;
import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.common.CommonInfo;
import com.liux.musicrelaxes.http.Api.IpType;
import com.liux.musicrelaxes.http.HttpManager;
import com.liux.musicrelaxes.http.RetrofitManager;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.utils.DateUtils;
import com.liux.musicrelaxes.utils.EncryptUtil;
import com.liux.musicrelaxes.utils.FileUtil;
import com.liux.musicrelaxes.utils.GsonUtils;
import com.liux.musicrelaxes.view.ToolbarHelper;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import butterknife.BindView;
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
 * file_no: 1699f32a7d7efe51886b30f77226949d_72788
 *
 * @author 750954283(qq)
 */

public class BlueToothWeCardioActivity extends BaseSwipeBackActivity {


    @BindView(R.id.et_Discomfort)
    EditText etDiscomfort;
    @BindView(R.id.btn_Up_BlueTooth_Data)
    Button btnUpBlueToothData;
    private String file_no;

    private String sBluetoothData;
    private String readString;


    private String heartRateRecordId;
    private String studentId;
    private double LF;
    private double HF;
    private double LFHF;
    private String weCardioReportData;
    private String weCardioConclusion;
    private String pdfUrl;

    private ACache mCache;

    private HeartRateRecordViewDto.DataBean heartRateRecordDataBean;




    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_blue_tooth_we_cardio;
    }

    @Override
    protected void initView() {
        mCache = ACache.get(MRApplication.getContext());
        studentId = mCache.getAsString("studentId");
        heartRateRecordDataBean = (HeartRateRecordViewDto.DataBean) mCache.getAsObject("HeartRateRecordDataBean");
        heartRateRecordId = heartRateRecordDataBean.getId();

        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "hello.txt";
        readString = FileUtil.readString(filePath, "utf-8");
        Logger.e(readString);
        ArrayList<String> blueToothData = getIntent().getStringArrayListExtra("BlueToothData");

        if (blueToothData != null && blueToothData.size() > 0) {
            sBluetoothData = blueToothData.toString();
            Logger.e(sBluetoothData);
        }
    }

    /**
     * 上传蓝牙心率的数据
     */
    public void upData() {
        showNormalDialog("正在上传，请稍后.....");
        String currentTime = DateUtils.getCurrentTimeToday(); //获取当前时间
        String dataOne = DateUtils.dataOne(currentTime); //当前时间转化成时间戳 10位

        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id", "11002");
        map.put("user_id", "188");
        map.put("stamp", dataOne);
        GsonUtils.toJson(map);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("app_id", "11002");
        hashMap.put("user_id", "188");
        hashMap.put("stamp", dataOne);
        hashMap.put("sign", signMD5(map));
        GsonUtils.toJson(hashMap);
        byte[] readStringBytes = readString.getBytes();
        Logger.e(readStringBytes.toString());
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), readString.getBytes());

        RetrofitManager.getInstance(IpType.BLUE_TOOTH_IP).getRetrofitService()
                .postBlueToothFileData(hashMap, requestFile).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String toothBean = response.body();
                    if (toothBean != null) {
                        UpLoadFileBean upLoadFileBean = GsonUtils.stringToObject(toothBean, UpLoadFileBean.class);
                        String data = upLoadFileBean.getData();
                        if (data != null) {
                            UpLoadDataBean upLoadDataBean = GsonUtils.stringToObject(data, UpLoadDataBean.class);
                            file_no = upLoadDataBean.getFile_no();
                            addData();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Logger.e(t.toString());
                dissMissNormalDialog();
            }
        });

    }

    private String signMD5(TreeMap<String, String> map) {
        String value = "";
        for (String key : map.keySet()) {
            value += map.get(key);
        }
        Logger.e(value);
        String signMD5 = EncryptUtil.makeMD5(value + CommonInfo.token + CommonInfo.companyKey);
        return signMD5;
    }

    /**
     * 添加用户心率测试记录
     */
    private void addData() {
        String condition = etDiscomfort.getText().toString();
        String currentTime = DateUtils.getCurrentTimeToday(); //获取当前时间
        String dataOne = DateUtils.dataOne(currentTime); //当前时间转化成时间戳 10位

        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("file_no",file_no);
        requestMap.put("test_time", dataOne);
        requestMap.put("type", "5");
        requestMap.put("condition", condition);
        String toJson = GsonUtils.toJson(requestMap);
        Logger.e(toJson);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), toJson);

        TreeMap<String, String> map = new TreeMap<>();
        map.put("app_id", "11002");
        map.put("user_id", "188");
        map.put("stamp", dataOne);
        map.put("file_no",file_no);
        map.put("test_time", dataOne);
        map.put("type", "5");
        map.put("condition", condition);
        GsonUtils.toJson(map);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("app_id", "11002");
        hashMap.put("user_id", "188");
        hashMap.put("stamp", dataOne);
        hashMap.put("sign", signMD5(map));
        GsonUtils.toJson(hashMap);

        RetrofitManager.getInstance(IpType.BLUE_TOOTH_IP).getRetrofitService()
                .postAddRecord(hashMap, requestBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String body = response.body(); //返回的整体数据
                    if (body != null) {
                        Logger.e(body);
                        String replaceJson = body.replace("{\\", "{").replace("\"{", "{").replace("}\"", "}")
                                .replace("}\\\"", "}").replace("\\\\\\", "").replace("\\\\", "").replace("\\", "");

                        weCardioReportData = replaceJson;
                        AddRecordDataBean addRecordDataBean = GsonUtils.stringToObject(replaceJson, AddRecordDataBean.class);
                        AddRecordDataBean.DataBean dataBean = addRecordDataBean.getData();
                        if (dataBean != null) {
                            pdfUrl = dataBean.getFile_report();// PDF地址
                            AddRecordDataBean.DataBean.ExtBean extBean = dataBean.getExt();
                            if (extBean != null) {
                                weCardioConclusion = extBean.getFindings(); //报告文字描述
                                AddRecordDataBean.DataBean.ExtBean.HrvBean hrvBean = extBean.getHrv();
                                if (hrvBean != null) {
                                    LF = hrvBean.getLf();
                                    HF = hrvBean.getHf();
                                    LFHF = LF / HF;
                                    //发送分析报告给服务器
                                    sendWeCardioData();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Logger.e(t.getMessage());
                dissMissNormalDialog();
            }
        });

    }

    /**
     * 将WeCard方的分析数据发送给自己公司的服务器上
     */
    private void sendWeCardioData() {
        SendWeCardioDataDto sendWeCardioDataDto = new SendWeCardioDataDto();
        sendWeCardioDataDto.setHeartRateRecordId(heartRateRecordId);     //用户心率测试的ID
        sendWeCardioDataDto.setStudentId(studentId);                    //学生ID
        sendWeCardioDataDto.setLF(LF);                                  //LF
        sendWeCardioDataDto.setHF(HF);                                  //HF
        sendWeCardioDataDto.setLFHF(LFHF);                              //值：LF/HF
        sendWeCardioDataDto.setWeCardioReportData(weCardioReportData);  //整个数据
        sendWeCardioDataDto.setWeCardioConclusion(weCardioConclusion);  //报告文字描述
        sendWeCardioDataDto.setImgurl(pdfUrl);
        HttpManager.getInstance().getApiService().postSendWeCardioData(sendWeCardioDataDto).enqueue(new Callback<ResultEntityDto>() {
            @Override
            public void onResponse(@NonNull Call<ResultEntityDto> call, @NonNull Response<ResultEntityDto> response) {
                dissMissNormalDialog();
                ResultEntityDto body = response.body();
                if (body != null && body.isSuccess()) {
                    Intent intent = new Intent(BlueToothWeCardioActivity.this, ChooseMusicActivity.class);
                    intent.putExtra("LFHF", LFHF);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultEntityDto> call, @NonNull Throwable t) {
                dissMissNormalDialog();
            }
        });
    }


    @OnClick(R.id.btn_Up_BlueTooth_Data)
    public void onViewClicked() {
        upData();
    }
}
