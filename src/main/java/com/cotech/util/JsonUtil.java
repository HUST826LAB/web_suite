package com.cotech.util;

import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public class JsonUtil {

    public MappingJacksonValue getJsonp(List lst, String callback){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lst);
        if(callback!=null){
            mappingJacksonValue.setJsonpFunction(callback);
        }
        return mappingJacksonValue;
    }
}
