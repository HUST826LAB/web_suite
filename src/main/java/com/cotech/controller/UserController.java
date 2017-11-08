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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
            user.setIp(res.getIp());
            user.setDevice(res.getDevice());
            user.setCreate_time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            user.setStatus(1);
            user.setLocation_id(Hashing.md5().newHasher().putString(user.getCreate_time()+new Random().nextLong(), Charsets.UTF_8).hash().toString());
            TUserService.saveUserSignUp(user);
        }catch (Exception e){
            logger.debug("参数校验错误"+user.toString()+e.getMessage()+"\n"+res.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject, callback);
        }
        try {
            res.setUser_id(TUserService.getUserIdByLocation(user.getLocation_id()));
            TResService.updateResUserId(res);
        }catch (Exception e){
            logger.debug("锁定成绩表失败,删除记录:"+e.getMessage());
            try{TUserService.deleteUserById(res.getUser_id());}
            catch (Exception e1){
                logger.warn("锁定成绩表失败后删除记录失败："+e1.getMessage()+"\nuser_id："+res.getUser_id());
                return JsonUtil.getInstense().getJsonp(jsonObject, callback);
            }
            return JsonUtil.getInstense().getJsonp(jsonObject, callback);
        }
        JSONObject resJson = new JSONObject();
        resJson.put("user_id",res.getUser_id());
        resJson.put("username",user.getUsername());
        WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),resJson);
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

    /**
     * 检查用户名接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "checkUsername")
    public MappingJacksonValue checkUsername(HttpServletRequest request, @RequestParam(value="callback",required=false) String callback) {
        logger.debug("注册接口收到来自" + request.getRemoteAddr() + "的请求！");
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJsonInString(jsonObject, Status.ParamError.getMsg(), Status.ParamError.getCode(), "该用户名已被注册!");
        String username = "";
        try {
            username = ParamCheck.paramNotEmptyNotNull(request.getParameter("username"));
            int flag = TUserService.countUsernameByUsername(username);
            if (flag==0)
                WrapJson.wrapJsonInString(jsonObject, Status.SUCCESS.getMsg(), Status.SUCCESS.getCode(), "该用户名可以使用!");
        }catch (Exception e){
            logger.debug("参数错误"+username);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

    /**
     * 登录接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "signIn")
    public MappingJacksonValue signInController(@RequestBody String param,@RequestParam(value="callback",required=false) String callback){
        JSONObject jsonObject = new JSONObject();
        TUser user = new TUser();
        TRes res = new TRes();
        WrapJson.wrapJson(jsonObject, Status.Fail.getMsg(), Status.Fail.getCode(), null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            user.setUsername(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("username")));
            user.setPassword(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("password")));
            //登录流程
            TUser flag = TUserService.getUserSignIn(user);
            if (flag!=null) {
                paramJson.clear();
                paramJson.put("user_id",flag.getUser_id());
                paramJson.put("status",flag.getStatus());
                WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(), Status.SUCCESS.getCode(), paramJson);
            }
            //登录成功，消费掉之前游戏的数据
            Long res_id = Long.valueOf((String) paramJson.get("res_id"));
            if (res_id > 0l){
                res.setRes_id(res_id);
                res.setUser_id(flag.getUser_id());
                //锁定库存
                TResService.updateResUserId(res);
                res = TResService.getResByID(res_id);
                //分数及金币处理
                if(res.getScore()>flag.getScore())
                    flag.setScore(res.getScore());
                flag.setGold(flag.getGold()+res.getGold());
                TUserService.updateGoldAndScoreById(flag);
            }


        }catch (Exception e){
            logger.debug("参数错误param="+param+e.getMessage()+"res_id:"+res.getRes_id());
        }


        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

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

        }catch (Exception e){
            logger.debug("参数错误param="+param+e.getMessage()+"res_id:"+res.getRes_id());
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

}
