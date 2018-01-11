package com.liux.musicrelaxes.http.Api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by
 * 项目名称：com.liux.bluetoothdemo.http.Api
 * 项目日期：2018/1/3
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class IpType {
    /**
     * 多少种IP类型
     */
    public static final int TYPE_COUNT = 2;

    /**
     * 公司IP地址
     */
    public static final int NB_CEI_IP = 1;

    /**
     * 心率测试公司IP地址
     */
    public static final int BLUE_TOOTH_IP = 2;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({NB_CEI_IP,BLUE_TOOTH_IP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IPTypeChecker{}
}
