package com.cotech.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public final class WrapJson<E> {

    private WrapJson() {}

    public static void wrapJson(JSONObject jsonObject, String msg, int code, JSONObject data){
        jsonObject.put("msg",msg);
        jsonObject.put("code",code);
        jsonObject.put("data",data);
    }

    public static void wrapJsonInString(JSONObject jsonObject, String msg, int code, String data){
        jsonObject.put("msg",msg);
        jsonObject.put("code",code);
        jsonObject.put("data",data);
    }
}
