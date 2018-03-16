package com.liux.module.BlueToothBean.heartRateRecord;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.sendWeCardio
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class SendWeCardioDataDto {

    /**
     * HeartRateRecordId : string  //GetCurrentHeartRateRecord 接口的ID
     * StudentId : string
     * LF : 0
     * HF : 0
     * LFHF : 0
     * WeCardioReportData : string  //心率的数据
     * WeCardioConclusion : string  //心率检查报告文字说明
     * Imgurl : string  //PDF的地址
     */

    private String HeartRateRecordId;
    private String StudentId;
    private double LF;
    private double HF;
    private double LFHF;
    private String WeCardioReportData;
    private String WeCardioConclusion;
    private String Imgurl;

    public String getHeartRateRecordId() {
        return HeartRateRecordId;
    }

    public void setHeartRateRecordId(String HeartRateRecordId) {
        this.HeartRateRecordId = HeartRateRecordId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public double getLF() {
        return LF;
    }

    public void setLF(double LF) {
        this.LF = LF;
    }

    public double getHF() {
        return HF;
    }

    public void setHF(double HF) {
        this.HF = HF;
    }

    public double getLFHF() {
        return LFHF;
    }

    public void setLFHF(double LFHF) {
        this.LFHF = LFHF;
    }

    public String getWeCardioReportData() {
        return WeCardioReportData;
    }

    public void setWeCardioReportData(String WeCardioReportData) {
        this.WeCardioReportData = WeCardioReportData;
    }

    public String getWeCardioConclusion() {
        return WeCardioConclusion;
    }

    public void setWeCardioConclusion(String WeCardioConclusion) {
        this.WeCardioConclusion = WeCardioConclusion;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public void setImgurl(String Imgurl) {
        this.Imgurl = Imgurl;
    }
}
