package com.cotech.controller;

import com.cotech.model.User;
import com.cotech.service.UserService;
import com.cotech.util.JsonUtil;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "test")
    public MappingJacksonValue test(@RequestParam(value="callback",required=false) String callback) {
        return new JsonUtil().getJsonp(userService.getList(),callback);
    }
}
