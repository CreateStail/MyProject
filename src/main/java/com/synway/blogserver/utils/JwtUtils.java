package com.synway.blogserver.utils;

import com.synway.blogserver.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    public static final String SUBJECT = "mySign";
//    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;//过期时间设置为一周,毫秒
    public static final long EXPIRE = 1000 * 60 * 30;//过期时间设置为一周,毫秒
    public static final String APPSECRET = "mx6666";//密钥

    /**
     * 生成jwt
     */
    public static String geneJsonWebToken(User user) {
        if (user == null || user.getId() == null || user.getNickname() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getNickname())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET)
                .compact();
        return token;
    }

    /**
     * 校验token
     */
    public static Claims checkJWT(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

}
