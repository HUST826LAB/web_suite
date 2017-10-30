package com.cotech.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public final class JsonUtil {

    private JsonUtil(){}

    private static JsonUtil jsonUtil = new JsonUtil();

    public static JsonUtil getInstense(){
        return jsonUtil;
    }

    public MappingJacksonValue getJsonp(JSONObject jsonObject, String callback){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(jsonObject);
        if(callback!=null){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }
}
