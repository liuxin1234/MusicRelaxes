package com.liux.musicrelaxes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.liux.module.BlueToothBean.ResultEntityDto;
import com.liux.module.BlueToothBean.heartRateRecord.HeartRateRecordViewDto;
import com.liux.module.BlueToothBean.heartRateRecord.SetMusicDto;
import com.liux.module.BlueToothBean.music.MusicListDto;
import com.liux.module.BlueToothBean.music.MusicParameterDto;
import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.adapter.ChooseMusicAdapter;
import com.liux.musicrelaxes.application.MRApplication;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.http.Api.ApiConstants;
import com.liux.musicrelaxes.http.HttpManager;
import com.liux.musicrelaxes.http.db.ACache;
import com.liux.musicrelaxes.music.MusicPlaylist;
import com.liux.musicrelaxes.music.data.Song;
import com.liux.musicrelaxes.service.MusicPlayerManager;
import com.liux.musicrelaxes.utils.GsonUtils;
import com.liux.musicrelaxes.view.ToolbarHelper;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2017/12/27
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ChooseMusicActivity extends BaseSwipeBackActivity {


    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.recycler_View)
    RecyclerView recyclerView;

    private ChooseMusicAdapter mMusicAdapter;
    private List<MusicListDto.DataBean.RowsBean> mRowsBeanList = new ArrayList<>();
    private double lfhf;
    private String musicId;
    private String musicFileUrl;

    private ACache mCache;
    private HeartRateRecordViewDto.DataBean heartRateRecordDataBean;
    private String HeartRateRecordId;
    private String selectMusicFileUrl;


    private List<MusicListDto.DataBean.RowsBean> rows = new ArrayList<>();
    private List<Song> mSongList = new ArrayList<>();
    private MusicPlaylist musicPlayList;

    private int prePosition = -1; //点击当前试听按钮position
    private int btnRadioPosition = -1; //当前选择的曲目position

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_choose_music;
    }

    @Override
    protected void initView() {
        musicPlayList = new MusicPlaylist();
        initPremission();


        mCache = ACache.get(MRApplication.getContext());
        heartRateRecordDataBean = (HeartRateRecordViewDto.DataBean) mCache.getAsObject("HeartRateRecordDataBean");
        HeartRateRecordId = heartRateRecordDataBean.getId();
        lfhf = getIntent().getDoubleExtra("LFHF", 0.0);

        mRowsBeanList = (List<MusicListDto.DataBean.RowsBean>) mCache.getAsObject("musicRows");
        if (mRowsBeanList == null){
            mRowsBeanList = new ArrayList<>();
        }

        initRecyclerAdapter();

        getMusicByLFHFData();
    }

    /**
     * 添加读写权限
     */
    private void initPremission() {
        //权限判断，如果没有权限就请求权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
        }
    }



    private void initRecyclerAdapter() {
        mMusicAdapter = new ChooseMusicAdapter(this,R.layout.include_choose_music,mRowsBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mMusicAdapter);
        mMusicAdapter.setOnOtherClickLisenter(new ChooseMusicAdapter.onOtherClickLinsenter() {
            @Override
            public void onRadioClickLinsenter(View v, int position) {
                musicId = mRowsBeanList.get(position).getId();
                selectMusicFileUrl = mRowsBeanList.get(position).getFileUrl();
                btnRadioPosition = position;
            }

            @Override
            public void onImgClickLinsenter(View v, int position) {
                musicFileUrl = mRowsBeanList.get(position).getFileUrl();

                //播放音乐
                MusicPlayerManager.get().playQueue(musicPlayList, position);


            }
        });
    }


    /**
     * 获取音乐数据
     */
    private void getMusicByLFHFData() {
        MusicParameterDto parameterDto = new MusicParameterDto();
        parameterDto.setHeartRateRecordId(HeartRateRecordId);
        parameterDto.setLFHF(lfhf);  //其值为： lf/hf
        GsonUtils.toJson(parameterDto);
        HttpManager.getInstance().getApiService().postMusicByLFHF(parameterDto).enqueue(new Callback<MusicListDto>() {
            @Override
            public void onResponse(@NonNull Call<MusicListDto> call, @NonNull Response<MusicListDto> response) {
                MusicListDto body = response.body();
                if (body != null && body.isSuccess()) {
                    MusicListDto.DataBean data = body.getData();
                    if (data != null) {
                        rows = data.getRows();
                        if (rows != null && rows.size() > 0){
                            mRowsBeanList.clear();
                            mRowsBeanList.addAll(rows);
                            mMusicAdapter.notifyDataSetChanged();

                            mCache.put("musicRows", (Serializable) mRowsBeanList);

                            for (int i=0;i<rows.size();i++){
                                Song song = new Song();
                                song.setId(rows.get(i).getId());
                                song.setTitle(rows.get(i).getName());
                                song.setAlbumId(rows.get(i).getMusicCategoryId());
                                song.setAlbumName(rows.get(i).getMusicCategoryName());
                                song.setPath(ApiConstants.IP_NB_CEI_MUSIC+rows.get(i).getFileUrl());
                                mSongList.add(song);
                            }
                            Logger.e(mSongList.toString());
                            musicPlayList.setQueue(mSongList);

                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MusicListDto> call, @NonNull Throwable t) {

            }
        });
    }


    /**
     * 发送选择好的音乐给服务器
     */
    private void upSelectMusicData(){
        if (musicId == null){
            Toast.makeText(this, "请先选择一首曲目", Toast.LENGTH_SHORT).show();
            return;
        }
        SetMusicDto musicDto = new SetMusicDto();
        musicDto.setHeartRateRecordId(HeartRateRecordId);
        musicDto.setMusicId(musicId);
        HttpManager.getInstance().getApiService().postSetMusicData(musicDto).enqueue(new Callback<ResultEntityDto>() {
            @Override
            public void onResponse(@NonNull Call<ResultEntityDto> call, @NonNull Response<ResultEntityDto> response) {
                ResultEntityDto body = response.body();
                if (body != null && body.isSuccess()){
                    MusicPlayerManager.get().playQueue(musicPlayList, btnRadioPosition);
                    Intent intent = new Intent(ChooseMusicActivity.this, PlayMusicActivity.class);
                    intent.putExtra("musicUrl",selectMusicFileUrl);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultEntityDto> call, @NonNull Throwable t) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this, "拒绝权限，将无法使用程序。", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        MusicPlayerManager.get().stop();
        upSelectMusicData();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicPlayerManager.get().stop();
    }
}
