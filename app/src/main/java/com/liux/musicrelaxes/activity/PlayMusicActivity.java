package com.liux.musicrelaxes.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.view.ToolbarHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2017/12/27
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class PlayMusicActivity extends BaseSwipeBackActivity {

    @BindView(R.id.btn_hear)
    Button btnHear;
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_Upper)
    Button btnUpper;
    @BindView(R.id.btn_Play)
    Button btnPlay;
    @BindView(R.id.sb_Progress)
    SeekBar sbProgress;
    @BindView(R.id.btn_Next)
    Button btnNext;

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initView() {
        sbProgress.setSecondaryProgress(0);

        //权限判断，如果没有权限就请求权限
        if (ContextCompat.checkSelfPermission(PlayMusicActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayMusicActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initMediaPlay();
        }
    }

    private void initMediaPlay(){
        try {
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            mMediaPlayer.setDataSource("http://m10.music.126.net/20171228172939/ee08bd6e0114bbbc76c4e82b831ca382/ymusic/2d41/4aaa/84d6/13c8de4c306bc7b108ec51c7534166e7.mp3");//指定音频文件路径
            mMediaPlayer.setLooping(true);//设置为循环播放
            mMediaPlayer.prepare();//初始化播放器MediaPlayer
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @OnClick({R.id.btn_Upper, R.id.btn_Play, R.id.btn_Next,R.id.btn_hear, R.id.btn_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Upper:
                break;
            case R.id.btn_Play:
                if (!mMediaPlayer.isPlaying()){
                    //如果没在播放中，立刻开始播放
                    mMediaPlayer.start();
                    btnPlay.setBackgroundResource(R.drawable.pause);
                    sbProgress.setMax(mMediaPlayer.getDuration());
                    //获取当前播放进度
                    int currentPosition = mMediaPlayer.getCurrentPosition();
                    sbProgress.setProgress(currentPosition);
                }else {
                    //如果在播放中，立刻暂停。
                    mMediaPlayer.pause();
                    btnPlay.setBackgroundResource(R.drawable.play);
                }
                break;
            case R.id.btn_Next:
                break;
            case R.id.btn_hear:
                Toast.makeText(this, "点击了继续聆听", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test:
                Toast.makeText(this, "点击了重新检测", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlay();
                }else {
                   Toast.makeText(this, "拒绝权限，将无法使用程序。", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }


}
