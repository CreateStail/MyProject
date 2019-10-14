package com.synway;

import com.synway.domain.User;
import com.synway.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class CommonTest {

    @Test
    public void testGeneJwt() {
        User user = new User();
        user.setId(99);
        user.setHeadImg("www.xdclass.net");
        user.setName("mx");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void testCheck() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJteFNpZ24iLCJpZCI6OTksIm5hbWUiOiJteCIsImltZyI6Ind3dy54ZGNsYXNzLm5ldCIsImlhdCI6MTU2ODQ1NTQ4MCwiZXhwIjoxNTY5MDYwMjgwfQ.yUBoWdqjwU36kSaVXzI1jTbtILvp75aZYXHkrkJ0qec";
        Claims claims = JwtUtils.checkJWR(token);
        if(claims != null){
            String id = String.valueOf(claims.get("id"));
            String name = String.valueOf(claims.get("name"));
            String img = String.valueOf(claims.get("img"));
            System.out.println(id + "__________" + name +"______________" + img);
        }else{
            System.out.println("非法token");
        }
    }


}
