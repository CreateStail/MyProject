package com.synway.blogserver.controller.admin;

import com.synway.blogserver.domain.User;
import com.synway.blogserver.service.UserService;
import com.synway.blogserver.utils.JsonData;
import com.synway.blogserver.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     *            Cookie cookie = new Cookie("token", token);
     *            cookie.setMaxAge(3600);
     * //          cookie.setDomain("localhost");
     *             cookie.setPath("/");
     *             response.addCookie(cookie);
     *             将token放入cookie中，目前不采用
     */
    @PostMapping("/login")
    public JsonData login(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          HttpServletResponse response){

        User user = userService.checkUser(username,password);
        if(user != null){
            String token = JwtUtils.geneJsonWebToken(user);
            session.setAttribute("user",user);
            return JsonData.buildSuccess(token);
        }else{
            return JsonData.buildError("用户不存在请重新输入.");
        }
    }

    @GetMapping("/logout")
    public JsonData logout(HttpSession session){
        session.removeAttribute("user");
        return JsonData.buildSuccess("已登出");
    }

}
