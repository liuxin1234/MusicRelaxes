package com.liux.module.BlueToothBean.student;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean.student
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class StudentDto {


    /**
     * Data : {"Id":"string","UserType":0,"Name":"string","OrganizationId":"string","OrganizationName":"string","LoginUserName":"string","Gender":true,"Birth":"2018-01-11T08:05:57.728Z","StuNo":"string","IdCard":"string","EduLevel":"string","Mobile":"string","Tel":"string","Address":"string","FatherOccupation":"string","MotherOccupation":"string","FamilyInformation":"string","PoliticsStatus":0,"Description":"string"}
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

    public static class DataBean {
        /**
         * Id : string
         * UserType : 0
         * Name : string
         * OrganizationId : string
         * OrganizationName : string
         * LoginUserName : string
         * Gender : true
         * Birth : 2018-01-11T08:05:57.728Z
         * StuNo : string
         * IdCard : string
         * EduLevel : string
         * Mobile : string
         * Tel : string
         * Address : string
         * FatherOccupation : string
         * MotherOccupation : string
         * FamilyInformation : string
         * PoliticsStatus : 0
         * Description : string
         */

        private String Id;
        private int UserType;
        private String Name;
        private String OrganizationId;
        private String OrganizationName;
        private String LoginUserName;
        private boolean Gender;
        private String Birth;
        private String StuNo;
        private String IdCard;
        private String EduLevel;
        private String Mobile;
        private String Tel;
        private String Address;
        private String FatherOccupation;
        private String MotherOccupation;
        private String FamilyInformation;
        private int PoliticsStatus;
        private String Description;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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

        public String getLoginUserName() {
            return LoginUserName;
        }

        public void setLoginUserName(String LoginUserName) {
            this.LoginUserName = LoginUserName;
        }

        public boolean isGender() {
            return Gender;
        }

        public void setGender(boolean Gender) {
            this.Gender = Gender;
        }

        public String getBirth() {
            return Birth;
        }

        public void setBirth(String Birth) {
            this.Birth = Birth;
        }

        public String getStuNo() {
            return StuNo;
        }

        public void setStuNo(String StuNo) {
            this.StuNo = StuNo;
        }

        public String getIdCard() {
            return IdCard;
        }

        public void setIdCard(String IdCard) {
            this.IdCard = IdCard;
        }

        public String getEduLevel() {
            return EduLevel;
        }

        public void setEduLevel(String EduLevel) {
            this.EduLevel = EduLevel;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getFatherOccupation() {
            return FatherOccupation;
        }

        public void setFatherOccupation(String FatherOccupation) {
            this.FatherOccupation = FatherOccupation;
        }

        public String getMotherOccupation() {
            return MotherOccupation;
        }

        public void setMotherOccupation(String MotherOccupation) {
            this.MotherOccupation = MotherOccupation;
        }

        public String getFamilyInformation() {
            return FamilyInformation;
        }

        public void setFamilyInformation(String FamilyInformation) {
            this.FamilyInformation = FamilyInformation;
        }

        public int getPoliticsStatus() {
            return PoliticsStatus;
        }

        public void setPoliticsStatus(int PoliticsStatus) {
            this.PoliticsStatus = PoliticsStatus;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }
    }

    public static class InfoBean {
    }
}
