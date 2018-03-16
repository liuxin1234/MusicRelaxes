package com.liux.musicrelaxes.service;

import android.support.v4.media.session.PlaybackStateCompat;

import com.liux.musicrelaxes.music.data.Song;


/**
 * @desciption: 歌曲变化监听器
 */
public interface OnSongChangedListener {
    /**
     * 歌曲改变的回调
     */
    void onSongChanged(Song song);

    /**
     * 歌曲后台改变的回调
     */
    void onPlayBackStateChanged(PlaybackStateCompat state);
}
