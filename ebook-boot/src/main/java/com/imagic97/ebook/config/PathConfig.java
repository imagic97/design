package com.imagic97.ebook.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author imagic
 */
@Configuration   //配置类注解，被自动扫描发现
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "system-config")
public class PathConfig {
    private  String bookPath;

    public  String getBookPath() {
        return bookPath;
    }
    public void setBookPath(String bookPath){
        this.bookPath = bookPath;
    }
}

