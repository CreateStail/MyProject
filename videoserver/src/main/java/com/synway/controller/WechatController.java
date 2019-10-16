package com.synway.controller;

import com.synway.config.WeChatConfig;
import com.synway.domain.User;
import com.synway.domain.VideoOrder;
import com.synway.service.UserService;
import com.synway.service.VideoOrderService;
import com.synway.utils.JsonData;
import com.synway.utils.JwtUtils;
import com.synway.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 拼装扫一扫url
     */
    @GetMapping("/login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true)String accessPage) throws UnsupportedEncodingException {
        String redirectUrl = weChatConfig.getOpenRedirectUrl();//获取开放平台重定向地址
        String callbackUrl = URLEncoder.encode(redirectUrl,"GBK");//
        String qrcodeUrl = String.format(WeChatConfig.OPEN_QRCODE_URL,weChatConfig.getOpenAppid(),callbackUrl,accessPage) ;
        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信登录回调
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code, String state, HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if(!state.startsWith("http")){
            state = "http://"+state;
        }
        if(user != null){
            String token = JwtUtils.geneJsonWebToken(user);
            response.sendRedirect(state+"?toekn="+token+"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName()));
        }
    }

    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request,HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while((line = in.readLine()) != null){
            stringBuffer.append(line);
        }
        in.close();
        inputStream.close();
        Map<String,String> callbackMap = WXPayUtil.xmlToMap(stringBuffer.toString());

        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        //判断签名是否正确
        if(WXPayUtil.isCorrectSign(sortedMap,weChatConfig.getKey())){
            if("SUCCESS".equals(sortedMap.get("result_code"))){
                String outTradeNo = sortedMap.get("out_trade_no");
                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                if(dbVideoOrder.getState() == 0){//判断逻辑看业务场景,订单状态为未支付的
                    //更新订单状态
                    VideoOrder videoOrder = new VideoOrder();
                    videoOrder.setOpenid(sortedMap.get("openid"));
                    videoOrder.setOutTradoNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    int rows = videoOrderService.updateVideoOrderByOutTradeNo(videoOrder);
                    if(rows == 1){//通知微信订单成功
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                    }
                }
            }
        }
       //处理失败

    }

}
