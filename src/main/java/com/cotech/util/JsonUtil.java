package com.cotech.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public class JsonUtil {

    public MappingJacksonValue getJsonp(List lst, String callback){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",0);
        jsonObject.put("result",0);
        jsonObject.put("data",lst);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(jsonObject);
        if(callback!=null){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }
}
