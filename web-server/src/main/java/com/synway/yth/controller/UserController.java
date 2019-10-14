package com.synway.yth.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.synway.yth.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /* paramType：表示参数放在哪个地方
    header-->请求参数的获取：@RequestHeader(代码中接收注解)
    query-->请求参数的获取：@RequestParam(代码中接收注解)
    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
    body-->请求参数的获取：@RequestBody(代码中接收注解)
    form（不常用）*/
    @ApiOperation(value = "获取用户信息",notes = "根据userId查询")
    @ApiImplicitParam(name = "userId",value = "用户ID",required = true,dataType = "String",paramType = "query")
    @RequestMapping(value = "/getUser.do",method = RequestMethod.POST)
    public Map<String,Object> findUserById(@RequestParam(required = true) String userId){
        logger.info("Controller接收到请求..." + "_______参数:" + userId);
        Map<String,Object> resultMap = userService.findUserById(userId);
        return resultMap;
    }


}
