package com.yxyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname SysConsunmerApplication
 * @Description TODO
 * @Date 2020/6/17 14:14
 * @Created by cw
 */
@SpringBootApplication
@EnableFeignClients
public class SysConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysConsumerApplication.class);
    }
}
