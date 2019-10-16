package com.synway.test;

import com.synway.config.WeChatConfig;
import com.synway.dao.VideoMapper;
import com.synway.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("/test")
    public String test(){
        System.out.println(weChatConfig.getAppid());
        return "hello";
    }


    @RequestMapping("/test_db")
    public JsonData testDB(){
        return JsonData.buildSuccess(videoMapper.findAll());
    }

}
