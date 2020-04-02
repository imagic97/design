package com.imagic97.ebook.util;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author imagic
 */

public class TokenUtil {

    public static long getTokenUserId() {
        HttpServletRequest httpServletRequest = getRequest();
        if (httpServletRequest != null) {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
            return Long.parseLong(JWT.decode(token).getAudience().get(0));
        }
        return -1;
    }

    public  static HttpSession getSession(){
        HttpServletRequest httpServletRequest = getRequest();
        if (httpServletRequest != null){
            return httpServletRequest.getSession();
        }
        return null;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}