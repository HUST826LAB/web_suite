package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.TGroup;
import com.cotech.model.TRes;
import com.cotech.model.TUser;
import com.cotech.service.TGroupService;
import com.cotech.service.TResService;
import com.cotech.service.TUserService;
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
public class MineController {

    @Resource
    private TGroupService TGroupService;

    @Resource
    private TUserService TUserService;

    @Resource
    private TResService TResService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 空间接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "zone")
    public MappingJacksonValue zoneController(@RequestBody String param,@RequestParam(value="callback",required=false) String callback){
        JSONObject jsonObject = new JSONObject();
        TUser user = new TUser();
        TRes res = new TRes();
        WrapJson.wrapJson(jsonObject, Status.Fail.getMsg(), Status.Fail.getCode(), null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            user.setUser_id(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            user = TUserService.getUserZone(user);
            JSONObject resJson = new JSONObject();
            resJson.put("gold",user.getGold());
            resJson.put("username",user.getUsername());
            resJson.put("uname",user.getUname());
            resJson.put("score",user.getScore());
            resJson.put("status",user.getStatus());
            //查询排名
            res.setGroup(user.getGroup());
            res.setScore(user.getScore());
            resJson.put("sum",TResService.countResCountCookieId(res));
            resJson.put("sum_level",TResService.countSumGreaterScore(user.getScore())+1);
            if ("0".equals(user.getGroup())) {
                resJson.put("group_name", TGroupService.getGroupName(user.getGroup()));
                resJson.put("sumGroup", TResService.countResCountGroup(res));
                resJson.put("group_level", TResService.conutSumGroupGreaterScore(res) + 1);
            }
            WrapJson.wrapJson(jsonObject,Status.SUCCESS.getMsg(), Status.SUCCESS.getCode(), resJson);
        }catch (Exception e){
            logger.debug("参数错误param="+param+e.getMessage()+"res_id:"+res.getRes_id());
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }


}
