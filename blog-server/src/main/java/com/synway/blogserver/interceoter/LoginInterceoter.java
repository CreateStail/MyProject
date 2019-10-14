package com.synway.blogserver.interceoter;

import com.synway.blogserver.utils.CommonUtils;
import com.synway.blogserver.utils.JsonData;
import com.synway.blogserver.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceoter implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }
        if(token != null){
            Claims claims = JwtUtils.checkJWT(token);
            if(claims != null){
                return true;
            }
        }
//        if(request.getSession().getAttribute("user") != null){
//            return true;
//        }
        logger.error("请求:"+request.getRequestURL()+"登录验证失败");
        CommonUtils.sendJsonMessage(response, JsonData.buildError("请登录"));
        return false;
    }
}
