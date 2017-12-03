package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.*;
import com.cotech.service.TGroupService;
import com.cotech.service.TResService;
import com.cotech.service.TUserService;
import com.cotech.util.JsonUtil;
import com.cotech.util.ParamCheck;
import com.cotech.util.WarpParam;
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
import java.util.*;

@Controller
public class UserController {

    @Resource
    private TUserService TUserService;

    @Resource
    private TResService TResService;

    @Resource
    private TGroupService TGroupService;

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
        try{
            ParamCheck.paramNotZeroNotNull(res.getGroup());
            TGroup group = TGroupService.getGroup(res.getGroup());
            group.setNumSum(group.getNumSum()+1);
            group.setScore(user.getScore());
            TGroupService.updateScoreById(group);
            TGroupService.updateNumById(group);
        }catch (Exception e){
            logger.debug("没有组别"+res.toString()+e.getMessage());
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
        logger.debug("checkUsername接口收到来自" + request.getRemoteAddr() + "的请求！");
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
    public MappingJacksonValue signInController(HttpServletRequest request,@RequestBody String param,@RequestParam(value="callback",required=false) String callback){
        logger.debug("signIn接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
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
            logger.debug("参数错误:"+e.getMessage()+"res_id:"+res.getRes_id());
        }


        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }

    /**
     *
     * @param request
     * @param param
     * @param callback
     * @return
     *
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "userDetail")
    public MappingJacksonValue userDetailController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("userDetail接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        PageVo pageVo = new PageVo();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            pageVo.setCurrent(ParamCheck.paramNotNull(paramJson.getLong("current")));
            pageVo.setPageLen(ParamCheck.paramNotZeroNotNull(paramJson.getLong("pageLen")));
            pageVo.setCurrent(pageVo.getCurrent()*pageVo.getPageLen());
            pageVo.setKey(ParamCheck.paramNotEmptyNotNull(paramJson.getString("user_id")));
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try {
            List<TRes> lst = TResService.selectResByUserID(pageVo);
            Long sum = TResService.countResUserId(pageVo);
            JSONObject resJson = new JSONObject();
            if (sum % pageVo.getPageLen() == 0)
                sum = sum / pageVo.getPageLen();
            else
                sum = sum / pageVo.getPageLen() + 1;
            resJson.put("sum",sum);
            resJson.put("userList",lst);
            WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),resJson);
        }catch (Exception e){
            logger.debug("操作数据库错误:"+e.getMessage());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }

    /**
     *
     * @param request
     * @param param
     * @param callback
     * @return
     *
     * 用户游戏详情接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "userGameDetail")
    public MappingJacksonValue userGameDetailController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("userGameDetail接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        TRes res = new TRes();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            res.setRes_id(paramJson.getLong("res_id"));
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try {
            res = TResService.getGameDetail(res);
            JSONObject resJson = new JSONObject();
            JSONObject detailJson = new JSONObject();
            JSONObject datumJson = (JSONObject) JSON.parse(res.getAddress());
            detailJson.put("res_id",res.getRes_id());
            detailJson.put("coordinate",res.getCoordinate());
            detailJson.put("type",res.getBlood());
            resJson.put("resDetail",detailJson);
            resJson.put("datum",datumJson);
            WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),resJson);
        }catch (Exception e){
            logger.debug("操作数据库错误:"+e.getMessage());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }

    //just for wrap
//    @ResponseBody
//    @RequestMapping(value = "warp")
//    public String warp() {
//        LinkedList<TRes> resList = TResService.selectAll();
//        while (resList.size()>0){
//            TRes res = resList.getFirst();
//            if (res.getAddress()!=null&&!"".equals(res.getAddress())){
//                if (res.getAddress().contains("triangle")){
//                    res.setBlood(1l);
//                    res.setAddress(WarpParam.wrapTriangle(res.getAddress()));
//                }else {
//                    res.setAddress(WarpParam.wrapCircle(res.getAddress()));
//                }
//                TResService.updateAll(res);
//            }
//            resList.removeFirst();
//        }
//        return "success";
//    }
}
