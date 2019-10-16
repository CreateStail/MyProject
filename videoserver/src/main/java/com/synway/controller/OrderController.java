package com.synway.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.synway.domain.VideoOrder;
import com.synway.service.VideoOrderService;
import com.synway.utils.IpUtils;
import com.synway.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器配置
 */
@RestController
@RequestMapping("/user/api/v1/order")
//@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping("/add")
    public void saveOrder(@RequestParam(value = "video_id",required = true)int video_id,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);
        int userId = 1;
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setUserId(userId);
        videoOrder.setVideoId(video_id);
        videoOrder.setIp(ip);
        String codeUrl = videoOrderService.save(videoOrder);
        //生成二维码
        if(codeUrl == null){
            throw new NullPointerException();
        }
        try {
            Map<EncodeHintType,Object> hint = new HashMap<>();
            //设置纠错等级
            hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hint.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hint);
            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
