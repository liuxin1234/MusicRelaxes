package com.liux.module.BlueToothBean.heartRateRecord;

import java.io.Serializable;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.sendWeCardio
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class HeartRateRecordViewDto implements Serializable{

    /**
     * Data : {"Version":0,"CreateTime":"2018-01-11T08:05:57.285Z","CreateUserId":"string","CreateUserName":"string","ModifyTime":"2018-01-11T08:05:57.285Z","ModifyUserId":"string","ModifyUserName":"string","Id":"string","OrganizationId":"string","OrganizationName":"string","StudentId":"string","MusicId":"string","StudentName":"string","TeacherId":"string","TeacherUserName":"string","TeacherRealName":"string","LF":0,"HF":0,"LFHF":0,"WeCardioReportData":"string","WeCardioConclusion":"string","Imgurl":"string","TeacherComment":"string","Description":"string","Status":0,"TestTime":"2018-01-11T08:05:57.285Z"}
     * Success : true
     * Message : string
     * Info : {}
     */

    private DataBean Data;
    private boolean Success;
    private String Message;
    private InfoBean Info;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class DataBean implements Serializable{
        /**
         * Version : 0
         * CreateTime : 2018-01-11T08:05:57.285Z
         * CreateUserId : string
         * CreateUserName : string
         * ModifyTime : 2018-01-11T08:05:57.285Z
         * ModifyUserId : string
         * ModifyUserName : string
         * Id : string
         * OrganizationId : string
         * OrganizationName : string
         * StudentId : string
         * MusicId : string
         * StudentName : string
         * TeacherId : string
         * TeacherUserName : string
         * TeacherRealName : string
         * LF : 0
         * HF : 0
         * LFHF : 0
         * WeCardioReportData : string
         * WeCardioConclusion : string
         * Imgurl : string
         * TeacherComment : string
         * Description : string
         * Status : 0   测试状态:  0、未测试,  1、报告已生成,  2、歌曲已分配, 3、测试已完成
         * TestTime : 2018-01-11T08:05:57.285Z
         */

        private int Version;
        private String CreateTime;
        private String CreateUserId;
        private String CreateUserName;
        private String ModifyTime;
        private String ModifyUserId;
        private String ModifyUserName;
        private String Id;
        private String OrganizationId;
        private String OrganizationName;
        private String StudentId;
        private String MusicId;
        private String StudentName;
        private String TeacherId;
        private String TeacherUserName;
        private String TeacherRealName;
        private double LF;
        private double HF;
        private double LFHF;
        private String WeCardioReportData;
        private String WeCardioConclusion;
        private String Imgurl;
        private String TeacherComment;
        private String Description;
        private int Status;
        private String TestTime;

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(String CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getModifyTime() {
            return ModifyTime;
        }

        public void setModifyTime(String ModifyTime) {
            this.ModifyTime = ModifyTime;
        }

        public String getModifyUserId() {
            return ModifyUserId;
        }

        public void setModifyUserId(String ModifyUserId) {
            this.ModifyUserId = ModifyUserId;
        }

        public String getModifyUserName() {
            return ModifyUserName;
        }

        public void setModifyUserName(String ModifyUserName) {
            this.ModifyUserName = ModifyUserName;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(String OrganizationId) {
            this.OrganizationId = OrganizationId;
        }

        public String getOrganizationName() {
            return OrganizationName;
        }

        public void setOrganizationName(String OrganizationName) {
            this.OrganizationName = OrganizationName;
        }

        public String getStudentId() {
            return StudentId;
        }

        public void setStudentId(String StudentId) {
            this.StudentId = StudentId;
        }

        public String getMusicId() {
            return MusicId;
        }

        public void setMusicId(String MusicId) {
            this.MusicId = MusicId;
        }

        public String getStudentName() {
            return StudentName;
        }

        public void setStudentName(String StudentName) {
            this.StudentName = StudentName;
        }

        public String getTeacherId() {
            return TeacherId;
        }

        public void setTeacherId(String TeacherId) {
            this.TeacherId = TeacherId;
        }

        public String getTeacherUserName() {
            return TeacherUserName;
        }

        public void setTeacherUserName(String TeacherUserName) {
            this.TeacherUserName = TeacherUserName;
        }

        public String getTeacherRealName() {
            return TeacherRealName;
        }

        public void setTeacherRealName(String TeacherRealName) {
            this.TeacherRealName = TeacherRealName;
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

        public String getTeacherComment() {
            return TeacherComment;
        }

        public void setTeacherComment(String TeacherComment) {
            this.TeacherComment = TeacherComment;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getTestTime() {
            return TestTime;
        }

        public void setTestTime(String TestTime) {
            this.TestTime = TestTime;
        }
    }

    public static class InfoBean {
    }
}
