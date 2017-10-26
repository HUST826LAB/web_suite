package com.cotech.controller;

import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
    public MappingJacksonValue indexController(HttpServletRequest request, @RequestParam(value="callback",required=false) String callback) {
        logger.debug("注册接口收到来自"+request.getRemoteAddr()+"的请求！");
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        TUser user = new TUser();
        try{
            user.setUsername(ParamCheck.paramNotEmptyNotNull(request.getParameter("username")));
            int flag = TUserService.countUsernameByUsername(user.getUsername());
            if (flag == 0)
                throw new Exception("参数错误");
            //必须玩过游戏的才允许注册
            String res_id = request.getParameter("res_id");
            user.setPassword(ParamCheck.paramNotEmptyNotNull(request.getParameter("password")));
            user.setIp(request.getParameter("ip"));
            user.setDevice(request.getParameter("device"));
            user.setCreate_time((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
            user.setStatus(0);
        }catch (Exception e){
            logger.debug("参数校验错误"+user.toString());
        }
        try{

        }catch (Exception e){

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
