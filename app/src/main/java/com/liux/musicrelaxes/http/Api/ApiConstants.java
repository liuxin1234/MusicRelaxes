package com.liux.musicrelaxes.http.Api;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.http.Api
 * 项目日期：2018/1/3
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ApiConstants {

    public static final String BASIC="NgAyAGIANgBhAGEAOAA1AC0ANQA1AGQAYQAtADQAOAAyAGQALQBiADgAYwAyAC0AOQAwAGYAYwBiADcANwBiADQAZgBhAGMAOgBkADkAMAAwADAAMgA0ADIALQBiADUANgAzAC0ANABkADUAMgAtADgAMAA5AGQALQAzADcAYQA0ADAAYQAxAGIAYwBhADQAMAA=";

    public static final String IP_BLUE_TOOTH = "http://test.wecardio.com:28090";

    public static final String IP_NB_CEI = "http://hrvapi.nbcei.net/";

    public static final String IP_NB_CEI_MUSIC = "http://hrv.nbcei.net";

    /**
     * 获取对应的ip
     *
     * @param ipType ip类型
     * @return host
     */
    public static String  getIP(int ipType){
        String ip;
        switch (ipType){
            case IpType.NB_CEI_IP:
                ip = IP_NB_CEI;
                break;
            case IpType.BLUE_TOOTH_IP:
                ip = IP_BLUE_TOOTH;
                break;
            default:
                ip = "";
                break;
        }
        return ip;
    }
}
