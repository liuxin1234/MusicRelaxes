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

public class ResetPwdDto {


    /**
     * Id : string
     * OldPassword : string
     * NewPassword : string
     */

    private String Id;
    private String OldPassword;
    private String NewPassword;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }
}
