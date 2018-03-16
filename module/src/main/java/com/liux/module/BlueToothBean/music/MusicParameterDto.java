package com.liux.module.BlueToothBean.music;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.music
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class MusicParameterDto {

    private String HeartRateRecordId;

    private Number LFHF; //其值为： lf/hf

    public String getHeartRateRecordId() {
        return HeartRateRecordId;
    }

    public void setHeartRateRecordId(String heartRateRecordId) {
        HeartRateRecordId = heartRateRecordId;
    }

    public Number getLFHF() {
        return LFHF;
    }

    public void setLFHF(Number LFHF) {
        this.LFHF = LFHF;
    }
}
