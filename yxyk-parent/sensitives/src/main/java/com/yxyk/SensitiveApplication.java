package com.yxyk;

import com.yxyk.listener.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：10:50
 */
@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class SensitiveApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SensitiveApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}