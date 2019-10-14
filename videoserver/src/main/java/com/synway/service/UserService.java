package com.synway.service;

import com.synway.config.WeChatConfig;
import com.synway.dao.UserMapper;
import com.synway.domain.User;
import com.synway.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    public User saveWeChatUser(String code){
        String accessTokenUrl = String.format(WeChatConfig.OPEN_ACCESS_TOKEN_URL,
                weChatConfig.getOpenAppid(),weChatConfig.getOpenAppsecret(),code);
        Map<String,Object> baseMap = HttpUtils.doGet(accessTokenUrl,5000);
        if(baseMap == null || baseMap.isEmpty()){return null;}
        String accessToken = (String)baseMap.get("access_token");
        String openId = (String)baseMap.get("openid");
        User dbUser = userMapper.findByopenid(openId);
        if(dbUser!=null){//更新用户,直接返回

        }
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL,accessToken,openId);
        Map<String,Object> baseUserMap = HttpUtils.doGet(userInfoUrl,5000);
        if(baseUserMap == null || baseUserMap.isEmpty()){return null;}
        String nickname = (String)baseUserMap.get("nickname");

        Double sexTemp = (Double)baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String)baseUserMap.get("province");
        String city = (String)baseUserMap.get("city");
        String country = (String)baseUserMap.get("country");
        String headimgurl = (String)baseUserMap.get("headimgurl");
        StringBuilder stringBuilder = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = stringBuilder.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"),"UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        user.setCity(finalAddress);
        userMapper.save(user);
        return user;
    }



}
