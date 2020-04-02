package com.imagic97.ebook.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.imagic97.ebook.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author imagic
 */
@Service("TokenService")
public class TokenService {

    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 24 * 10;//10天有效时间
        Date end = new Date(currentTime);
        String token;
        token = JWT.create().withAudience(Long.toString(user.getUserId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}