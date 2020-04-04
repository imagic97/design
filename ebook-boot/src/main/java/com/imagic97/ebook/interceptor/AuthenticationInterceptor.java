package com.imagic97.ebook.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.imagic97.ebook.annotation.PassToken;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.UserService;
import com.imagic97.ebook.util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author imagic
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
        // 执行认证
        if (token == null) {
            throw new MessageException("401", "请登录后再进行操作");
        }
        // 获取 token 中的 user id
        long userId;
        try {
            userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            throw new MessageException("401", "token错误");
        }

        //验证session中user是否存在
        HttpSession httpSession = TokenUtil.getSession();

        User sessionUser = (User) httpSession.getAttribute("user");
        if (sessionUser != null) {
            if (sessionUser.getUserId() == userId) {
                return true;
            }
        }

        User user = userService.selectUserById(userId);
        if (user == null) {
            throw new MessageException("401", "用户不存在，请重新登录");
        }
        if (user.getState() == 1) {
            throw new MessageException("401", "账户被冻结，拒绝登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new MessageException("401", "非法登录");
        }

        httpSession.setAttribute("user", user);

        return true;
//            }
//        }
//        return true;
    }
}