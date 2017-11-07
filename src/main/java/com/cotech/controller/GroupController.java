package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.TGroup;
import com.cotech.service.TGroupService;
import com.cotech.util.JsonUtil;
import com.cotech.util.ParamCheck;
import com.cotech.util.WrapJson;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Controller
public class GroupController {

    @Resource
    private TGroupService TGroupService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "newGroup")
    public MappingJacksonValue newGroupController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("newGroup接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        TGroup group = new TGroup();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            group.setCreator(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            group.setName(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("name")));
            group.setCreate_time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            group.setGroup_id(Hashing.sha1().newHasher().putString(group.getCreate_time()+new Random().nextLong(), Charsets.UTF_8).hash().toString());
            group.setOwner(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            group.setNumSum(0l);
            group.setScore(0l);
            TGroupService.saveGroup(group);
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage()+","+group.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        WrapJson.wrapJsonInString(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),group.getGroup_id());
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }


}
