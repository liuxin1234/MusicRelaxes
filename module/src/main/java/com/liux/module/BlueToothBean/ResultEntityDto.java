package com.liux.module.BlueToothBean;

/**
 * Created by
 * 项目名称：com.liux.module.BlueToothBean
 * 项目日期：2018/1/11
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ResultEntityDto {

    /**
     * Success : true
     * Message : string
     * Info : {}
     */

    private boolean Success;
    private String Message;
    private InfoBean Info;

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

    public static class InfoBean {
    }
}
