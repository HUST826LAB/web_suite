package com.cotech.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.GroupDetail;
import com.cotech.model.PageVo;
import com.cotech.model.TGroup;
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
import java.util.*;


@Controller
public class GroupController {

    @Resource
    private TGroupService TGroupService;

    @Resource
    private TUserService TUserService;

    @Resource
    private TResService TResService;

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
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage()+","+group.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try{
            Long count = TGroupService.countGroupName(group);
            if (count>0){
                WrapJson.wrapJsonInString(jsonObject, Status.Exist.getMsg(),Status.Exist.getCode(),TGroupService.getGroup_id(group));
                return JsonUtil.getInstense().getJsonp(jsonObject,callback);
            }else
                TGroupService.saveGroup(group);
        }catch (Exception e){
            logger.debug("操作数据库失败:"+e.getMessage()+","+group.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        WrapJson.wrapJsonInString(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),group.getGroup_id());
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "selectGroup")
    public MappingJacksonValue selectGroupController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("selectGroup接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        TGroup group = new TGroup();
        PageVo pageVo = new PageVo();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            pageVo.setCurrent(ParamCheck.paramNotNull(paramJson.getLong("current")));
            pageVo.setPageLen(ParamCheck.paramNotZeroNotNull(paramJson.getLong("pageLen")));
            pageVo.setCurrent(pageVo.getCurrent()*pageVo.getPageLen());
            List<TGroup> groupList = TGroupService.selectGroupByPage(pageVo);
            Long sum = TGroupService.countGroup();
            JSONObject resJson = new JSONObject();
            resJson.put("group_lst",groupList);
            if (sum % pageVo.getPageLen() == 0)
                sum = sum / pageVo.getPageLen();
            else
                sum = sum / pageVo.getPageLen() + 1;
            resJson.put("sum",sum);
            WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),resJson);
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage()+","+group.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "deleteGroup")
    public MappingJacksonValue deleteGroupController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("deleteGroup接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        TGroup group = new TGroup();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            group.setName(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("name")));
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage()+","+group.toString());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try {
            TGroupService.deleteGroupByName(group);
        }catch (Exception e){
            logger.debug("操作数据库错误:"+e.getMessage()+","+group.toString());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),null);
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "groupDetail")
    public MappingJacksonValue groupDetailController(HttpServletRequest request, @RequestBody String param, @RequestParam(value = "callback", required = false) String callback) {
        logger.debug("groupDetail接口收到来自" + request.getRemoteAddr() + "的请求！param="+param);
        JSONObject jsonObject = new JSONObject();
        PageVo pageVo = new PageVo();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            pageVo.setCurrent(ParamCheck.paramNotNull(paramJson.getLong("current")));
            pageVo.setPageLen(ParamCheck.paramNotZeroNotNull(paramJson.getLong("pageLen")));
            pageVo.setCurrent(pageVo.getCurrent()*pageVo.getPageLen());
            pageVo.setKey(ParamCheck.paramNotEmptyNotNull(paramJson.getString("group_id")));
        }catch (Exception e){
            logger.debug("参数错误:"+e.getMessage());
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        try {
            List<GroupDetail> lst = TUserService.selectUserIdByGroup(pageVo);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < lst.size(); i++)
                stringBuilder.append(lst.get(i).getUser_id()+",");
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            String keys = stringBuilder.toString();
            LinkedList<GroupDetail> lst2 = TResService.selectSumbyUserId(keys);
            Map<String,Integer> maps = new HashMap<String, Integer>();
            for (int i = 0; i < lst.size(); i++) {
                maps.put(lst.get(i).getUser_id(),i);
            }
            int index;
            while (lst2.size()>0){
                index = maps.get(lst2.getFirst().getUser_id());
                lst.get(index).setLastTime(lst2.getFirst().getLastTime());
                lst.get(index).setSum(lst2.getFirst().getSum());
                lst2.removeFirst();
            }
            JSONObject resJson = new JSONObject();
            Long sum = TUserService.countuserByGroup(pageVo);
            if (sum % pageVo.getPageLen() == 0)
                sum = sum / pageVo.getPageLen();
            else
                sum = sum / pageVo.getPageLen() + 1;
            resJson.put("sum",sum);
            resJson.put("groupLst",lst);
            WrapJson.wrapJson(jsonObject, Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),resJson);
        }catch (Exception e){
            logger.debug("操作数据库错误:"+e.getMessage());
            WrapJson.wrapJson(jsonObject, Status.Error.getMsg(),Status.Error.getCode(),null);
            return JsonUtil.getInstense().getJsonp(jsonObject,callback);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject,callback);
    }
}
