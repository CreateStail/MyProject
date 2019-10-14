package com.synway.interceoter;

import com.synway.utils.JsonData;
import com.synway.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.synway.utils.CommonUtils.sendJsonMessage;

public class LoginIntercepter implements HandlerInterceptor {
    /**
     * 进controller之前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }
        if(token != null){
            Claims claims = JwtUtils.checkJWR(token);
            if(claims != null){
                Integer userId = (Integer)claims.get("id");
                String name = (String)claims.get("name");
                request.setAttribute("user_id",userId);
                request.setAttribute("name",name);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));
        return false;
    }




}
