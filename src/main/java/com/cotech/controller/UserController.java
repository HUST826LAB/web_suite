package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.TRes;
import com.cotech.model.TUser;
import com.cotech.model.TopTenList;
import com.cotech.service.TResService;
import com.cotech.service.TUserService;
import com.cotech.util.JsonUtil;
import com.cotech.util.ParamCheck;
import com.cotech.util.WrapJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private TUserService TUserService;

    @Resource
    private TResService TResService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 注册接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "signUp")
    public MappingJacksonValue signUpController(@RequestBody String param, HttpServletRequest request, @RequestParam(value="callback",required=false) String callback) {
        logger.debug("注册接口收到来自"+request.getRemoteAddr()+"的请求！"+param);
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        TUser user = new TUser();
        TRes res = new TRes();
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            user.setUsername(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("username")));
            int flag = TUserService.countUsernameByUsername(user.getUsername());
            if (flag > 0)
                throw new Exception("用户名已被注册");
            //必须玩过游戏的才允许注册
            String res_id = ParamCheck.paramNotEmptyNotNull((String) paramJson.get("res_id"));
            res = TResService.getResByID(Long.valueOf(res_id));
            ParamCheck.paramIsZeroNotNull(res.getUser_id());
            user.setScore(res.getScore());
            user.setGold(res.getGold());
            user.setGroup(res.getGroup());
            user.setPassword(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("password")));
            user.setIp(request.getRemoteAddr());
            user.setDevice((String) paramJson.get("device"));
            user.setCreate_time((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
            user.setStatus(0);
            TUserService.saveUserSignUp(user);
        }catch (Exception e){
            logger.debug("参数校验错误"+user.toString()+e.getMessage()+"\n"+res.toString());
        }
        WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),null);
        try{
            ParamCheck.paramNotZeroNotNull(res.getReferee());
            int flag = TUserService.countUserCountById(res.getReferee());
            if (flag == 0)
                throw new Exception("推荐人不存在");
            user = TUserService.getUserScoreAndGoldByID(res.getReferee());
            user.setGold(user.getGold()+50l);
            TUserService.updateGoldAndScoreById(user);
        }catch (Exception e){
            logger.debug("没有推荐人"+res.toString()+e.getMessage());
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "checkUsername")
    public MappingJacksonValue checkUsername(HttpServletRequest request, @RequestParam(value="callback",required=false) String callback) {
        logger.debug("注册接口收到来自" + request.getRemoteAddr() + "的请求！");
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJsonInString(jsonObject, Status.ParamError.getMsg(), Status.ParamError.getCode(), "该用户名已被注册!");
        try {
            String username = ParamCheck.paramNotEmptyNotNull(request.getParameter("username"));
            int flag = TUserService.countUsernameByUsername(username);
            if (flag>0)
                WrapJson.wrapJsonInString(jsonObject, Status.SUCCESS.getMsg(), Status.SUCCESS.getCode(), "该用户名可以使用!");
        }catch (Exception e){
            logger.debug("参数错误");
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

    }
