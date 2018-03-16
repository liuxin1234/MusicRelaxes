package com.liux.musicrelaxes.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liux.musicrelaxes.R;
import com.liux.musicrelaxes.base.BaseSwipeBackActivity;
import com.liux.musicrelaxes.music.data.Song;
import com.liux.musicrelaxes.service.MusicPlayerManager;
import com.liux.musicrelaxes.service.OnSongChangedListener;
import com.liux.musicrelaxes.view.ToolbarHelper;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 * 项目名称：com.liux.musicrelaxes.activity
 * 项目日期：2017/12/27
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class PlayMusicActivity extends BaseSwipeBackActivity implements OnSongChangedListener {

    @BindView(R.id.btn_Upper)
    Button btnUpper;
    @BindView(R.id.btn_Play)
    Button btnPlay;
    @BindView(R.id.sb_Progress)
    SeekBar sbProgress;
    @BindView(R.id.btn_Next)
    Button btnNext;
    @BindView(R.id.tv_Title)
    TextView tvTitle;

    private Song mSong;
    private Handler mHandler = new Handler();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initView() {
        //权限判断，如果没有权限就请求权限
        if (ContextCompat.checkSelfPermission(PlayMusicActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayMusicActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMusicData();
        }
    }

    private void initMusicData() {
        mSong = MusicPlayerManager.get().getPlayingSong();
        MusicPlayerManager.get().registerListener(this); //添加歌曲改变的监听
        Logger.e(mSong.toString());
        if (mSong == null) {
            finish();
        }
        btnPlay.setBackgroundResource(R.drawable.pause);

        initProgressListener();
        updateData();
        mHandler.post(mRunnable);
    }

    /***
     * 更新数据
     */
    private void updateData() {
        if (mSong != null) {
            tvTitle.setText(mSong.getTitle());
            Logger.e(""+mSong.toString());
        }

    }


    private void initProgressListener() {
        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                /* * 进度条拖拉 * */
                if (fromUser) {
                    MusicPlayerManager.get().seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 运行线程  更新进度条，进度显示，歌曲长度
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                sbProgress.setMax(MusicPlayerManager.get().getCurrentMaxDuration());
                sbProgress.setProgress(MusicPlayerManager.get().getCurrentPosition());
                mHandler.postDelayed(mRunnable, 400);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };





    @OnClick({R.id.btn_Upper, R.id.btn_Play, R.id.btn_Next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Upper:
                MusicPlayerManager.get().playPrev();
                updateData();
                break;
            case R.id.btn_Play:
                if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PLAYING) {
                    MusicPlayerManager.get().pause();
                    btnPlay.setBackgroundResource(R.drawable.play);
                } else if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PAUSED) {
                    MusicPlayerManager.get().play();
                    btnPlay.setBackgroundResource(R.drawable.pause);
                }

                break;
            case R.id.btn_Next:
                MusicPlayerManager.get().playNext();
                updateData();
                break;

            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMusicData();
                } else {
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
        mHandler.removeCallbacks(mRunnable); // 销毁线程
        MusicPlayerManager.get().stop();
        MusicPlayerManager.get().unregisterListener(this); //取消歌曲改变监听
    }


    @Override
    public void onSongChanged(Song song) {
        this.mSong = song;
        updateData();
    }

    @Override
    public void onPlayBackStateChanged(PlaybackStateCompat state) {
        updatePlayStatus();
    }

    private void updatePlayStatus() {
        if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PLAYING) {

            btnPlay.setBackgroundResource(R.drawable.pause);
        } else if (MusicPlayerManager.get().getState() == PlaybackStateCompat.STATE_PAUSED) {
            btnPlay.setBackgroundResource(R.drawable.play);

        }
    }

}
