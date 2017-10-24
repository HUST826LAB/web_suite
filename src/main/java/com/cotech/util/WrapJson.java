package com.cotech.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class WrapJson {
    private static WrapJson ourInstance = new WrapJson();

    public static WrapJson getInstance() {
        return ourInstance;
    }

    private WrapJson() {
    }

    public void wrapJson(JSONObject jsonObject, String msg, int code, List data){
        jsonObject.put("msg",msg);
        jsonObject.put("code",code);
        jsonObject.put("data",data);
    }
}
