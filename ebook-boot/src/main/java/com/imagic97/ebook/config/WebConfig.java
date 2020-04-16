package com.imagic97.ebook.config;

import com.imagic97.ebook.interceptor.AdminInterceptor;
import com.imagic97.ebook.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/read/*")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/swagger-resources/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/swagger-resources/configuration/security")
                .excludePathPatterns("/csrf");

        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/login");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();// 自己写的拦截器
    }

    @Bean
    public AdminInterceptor adminInterceptor() {
        return new AdminInterceptor();// 自己写的拦截器
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*"). //允许跨域的域名，可以用*表示允许任何域名使用
                allowedMethods("*"). //允许任何方法（post、get等）
                allowedHeaders("*"). //允许任何请求头
                allowCredentials(true). //带上cookie信息
                exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
    }
}
