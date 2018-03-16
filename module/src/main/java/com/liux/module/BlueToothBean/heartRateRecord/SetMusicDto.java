package com.liux.module.BlueToothBean.heartRateRecord;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.heartRateRecord
 * 项目日期：2018/1/17
 * 作者：liux
 * 功能：用户选择了音乐的  实体类
 *
 * @author 750954283(qq)
 */

public class SetMusicDto {

    /**
     * HeartRateRecordId : string
     * MusicId : string
     */

    private String HeartRateRecordId;
    private String MusicId;

    public String getHeartRateRecordId() {
        return HeartRateRecordId;
    }

    public void setHeartRateRecordId(String HeartRateRecordId) {
        this.HeartRateRecordId = HeartRateRecordId;
    }

    public String getMusicId() {
        return MusicId;
    }

    public void setMusicId(String MusicId) {
        this.MusicId = MusicId;
    }
}
