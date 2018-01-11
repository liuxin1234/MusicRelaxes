package com.liux.musicrelaxes.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * Created by
 * 项目名称：com.liux.easyreading.utils
 * 项目日期：2017/11/6
 * 作者：liux
 * 功能：
 * @author 75095
 */

public class GsonUtils {
    public static <T> T object(Map<String,Object> map, Class<T> tClass){
        Gson gson = new Gson();
        String json = gson.toJson(map);
        Logger.json(json);
        return gson.fromJson(json, tClass);
    }

    public static <T> String toJson(Object object){
        Gson gson = new Gson();
        Logger.json(gson.toJson(object));
        return gson.toJson(object);
    }

    public static <T> T stringToObject(String s, Class<T> tClass){
        JsonObject obj = new JsonParser().parse(s).getAsJsonObject();
        Gson gson = new Gson();
        Logger.json(s);
        return gson.fromJson(obj, tClass);
    }



}
