package com.cotech.controller;

import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.TUser;
import com.cotech.service.TUserService;
import com.cotech.util.JsonUtil;
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
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private TUserService TUserService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "test")
    public MappingJacksonValue test(HttpServletRequest request, @RequestParam(value="callback",required=false) String callback) {
        logger.debug("test接口收到来自"+request.getRemoteAddr()+"的请求！");
        List<TUser> lst = null;
        JSONObject jsonObject = new JSONObject();
        try {
             lst = TUserService.getList();
        }catch (Exception e){
            logger.error("test接口查询数据库出现问题:"+e.getMessage());
            WrapJson.getInstance().wrapJson(jsonObject,Status.Error.getMsg(),Status.Error.getCode(),null);
        }
        if (lst.isEmpty()||lst==null){
            WrapJson.getInstance().wrapJson(jsonObject,Status.Fail.getMsg(),Status.Fail.getCode(),null);
        }else {
            WrapJson.getInstance().wrapJson(jsonObject,Status.SUCCESS.getMsg(),Status.SUCCESS.getCode(),lst);
        }
        return JsonUtil.getInstense().getJsonp(jsonObject, callback);
    }
}
