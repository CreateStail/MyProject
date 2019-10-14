package com.synway.service;


import com.synway.config.WeChatConfig;
import com.synway.dao.UserMapper;
import com.synway.dao.VideoMapper;
import com.synway.dao.VideoOrderMapper;
import com.synway.domain.User;
import com.synway.domain.Video;
import com.synway.domain.VideoOrder;
import com.synway.utils.CommonUtils;
import com.synway.utils.HttpUtils;
import com.synway.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class VideoOrderService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoOrderMapper videoOrderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatConfig weChatConfig;

    @Transactional(propagation = Propagation.REQUIRED)
    public String save(VideoOrder videoOrder) throws Exception {
        //查找视频信息
        Video video = videoMapper.findById(videoOrder.getVideoId());
        //查找用户信息
        User user = userMapper.findByid(videoOrder.getUserId());
        //生成订单
        VideoOrder videoOrderPro = new VideoOrder();
        videoOrderPro.setTotalFee(videoOrder.getTotalFee());
        videoOrderPro.setVideoImg(videoOrder.getVideoImg());
        videoOrderPro.setVideoTitle(videoOrder.getVideoTitle());
        videoOrderPro.setCreateTime(new Date());
        videoOrderPro.setState(0);
        videoOrderPro.setUserId(user.getId());
        videoOrderPro.setHeadImg(user.getHeadImg());
        videoOrderPro.setNickname(user.getName());
        videoOrderPro.setDel(0);
        videoOrderPro.setIp(videoOrder.getIp());
        videoOrderPro.setVideoId(video.getId());
        videoOrderMapper.insert(videoOrder);
        String codeUrl = unifiedOrder(videoOrder);
        return codeUrl;
    }

    /**
     * 统一下单方法
     * @param videoOrder
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {
        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",weChatConfig.getAppid());
        params.put("mch_id",weChatConfig.getMchId());
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("sign_type","MD5");
        params.put("body",videoOrder.getVideoTitle());
        params.put("out_trade_no",videoOrder.getOutTradoNo());
        params.put("total_fee",String.valueOf(videoOrder.getTotalFee()));
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");
        //sign签名
        String sign = WXPayUtil.createSign(params, weChatConfig.getKey());
        params.put("sign",sign);
        //参数转换mapToXml
        String payXml = WXPayUtil.mapToXml(params);
        System.out.println(payXml);
        //统一下单
        String orderStr = HttpUtils.doPost(WeChatConfig.UNIFIED_ORDER_URL, payXml, 4000);
        if(null == orderStr){
            return null;
        }
        Map<String,String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap.toString());
        if(unifiedOrderMap != null){
            return unifiedOrderMap.get("code_url");
        }
        return "";
    }

    public VideoOrder findByOutTradeNo(String outTradeNo){
        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrder){
        return videoOrderMapper.updateVideoOrderByOutTradeNo(videoOrder);
    }



}
