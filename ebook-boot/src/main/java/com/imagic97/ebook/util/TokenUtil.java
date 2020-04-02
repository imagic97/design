package com.imagic97.ebook.util;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author imagic
 */

public class TokenUtil {

    public static long getTokenUserId() {
        HttpServletRequest httpServletRequest = getRequest();
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        return Long.parseLong(JWT.decode(token).getAudience().get(0));
    }

    public static HttpSession getSession() {
        HttpServletRequest httpServletRequest = getRequest();
        return httpServletRequest.getSession();
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return Objects.requireNonNull(requestAttributes).getRequest();
    }

    /**
     * 获取request
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return Objects.requireNonNull(requestAttributes).getResponse();
    }
}