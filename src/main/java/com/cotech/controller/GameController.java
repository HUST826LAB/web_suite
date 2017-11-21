package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.Circle;
import com.cotech.model.TRes;
import com.cotech.model.TUser;
import com.cotech.service.GameService;
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

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;


@Controller
public class GameController {
    @Resource
    private GameService gameService;
    @Resource
    private TResService TResService;
    @Resource
    private TUserService TUserService;
    @Resource
    private TGroupService TGroupService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 游戏主流程接口
     * author:陈震威
     * bug联系方式：zhenweichen.ron@foxmail.com
     * 敬祝码祺
     */
    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "gameMain")
    public MappingJacksonValue gameMainController(HttpServletRequest request,@RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("gameMain接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        TRes res = new TRes();
        Circle circle = new Circle();
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            if(paramJson.containsKey("summit")){
                res.setAddress("triangle:"+paramJson.getString("summit"));
            }else if (paramJson.containsKey("circle_r")){
                circle.setCircle_r(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_r"))));
                circle.setCircle_y(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_y"))));
                circle.setCircle_x(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_x"))));
                res.setAddress(circle.toString());
            }
            res.setCookie_id(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("cookie_id")));
            res.setCoordinate(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("coordinate")));
            res.setTime_len(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("time_len"))));
            res.setDeviation(ParamCheck.paramNotEmptyNotNull(paramJson.getString("deviation")));
            res.setScore(ParamCheck.paramGreatThanZeroNotNull(paramJson.getLong("score")));
            res.setGold(10 + res.getScore()/100);
            res.setDevice((String) paramJson.get("device"));
            res.setIp(request.getRemoteAddr());
            res.setGroup(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("group")));
            res.setCreate_time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            res.setReferee(checkNotNull(Long.valueOf((String) paramJson.get("referee"))));
            res.setUser_id(checkNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            res.setLocation_id(Hashing.md5().newHasher().putString(res.getCreate_time()+new Random().nextLong(), Charsets.UTF_8).hash().toString());
        } catch (SQLException e){
            logger.debug("不是圆");
            WrapJson.wrapJson(jsonObject, Status.NotCircle.getMsg(),Status.NotCircle.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        } catch (Exception e){
            logger.debug("参数校验出错！"+res.toString()+circle.toString());
            logger.debug("错误原因："+e.getMessage());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try{
            TResService.saveGameMain(res);
            res.setRes_id(TResService.getResIdByLocationId(res.getLocation_id()));
        }catch (Exception e){
            logger.debug("录入游戏信息出错！"+e.getMessage());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        //检查是否登录，若登录增加金币更新分数和偏移率
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("res_id",res.getRes_id());
//        jsonObject1.put("score",res.getScore());
        jsonObject1.put("gold",res.getGold());
        try{
            long sum = TResService.countResCountCookieId(res);
            long sumL = TResService.countSumGreaterScore(res.getScore());
            if (sum != sumL)
                sumL++;
            jsonObject1.put("sum",sum);
            jsonObject1.put("sum_level",sumL);
            if (!"0".equals(res.getGroup())) {
                sum = TResService.countResCountGroup(res);
                sumL = TResService.countSumGroupGreaterScore(res);
                if (sumL != sum) {
                    sumL++;
                }
                jsonObject1.put("group_name", TGroupService.getGroupName(res.getGroup()));
                jsonObject1.put("sumGroup", sum);
                jsonObject1.put("group_level", sumL);
            }
        }catch (Exception e){
            logger.debug("查询排名出错"+e.getMessage());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),jsonObject1);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),jsonObject1);
        if (res.getUser_id()==0)
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        try {
            Long user_id = res.getUser_id();
            int flag = TUserService.countUserCountById(user_id);
            if (flag == 0)
                return JsonUtil.getInstense().getJsonp(jsonObject, callback);
            TUser user = TUserService.getUserScoreAndGoldByID(user_id);
            Long gold = user.getGold();
            user.setGold(gold+res.getGold());
            Long score = user.getScore();
            res.setUser_id(user.getUser_id());
//            res.setAddress(user.getAddress());
            res.setSex(user.getSex());
            res.setConstellation(user.getConstellation());
            res.setBlood(user.getBlood());
            //更新res表
            TResService.updateResDetail(res);
            if ((score < res.getScore())) {
                user.setScore(res.getScore());
            }
            TUserService.updateGoldAndScoreById(user);
            jsonObject1.remove("res_id");
            WrapJson.wrapJson(jsonObject, Status.Logged.getMsg(),Status.Logged.getCode(),jsonObject1);
        }catch (Exception e){
            logger.debug("关联已注册用户失败"+e.getMessage());
        }
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }
}
