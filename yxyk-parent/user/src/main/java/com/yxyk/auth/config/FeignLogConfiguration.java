package com.yxyk.auth.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TieYan
 * @date 2017/6/9
 */
@Slf4j
@Configuration
public class FeignLogConfiguration {
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
}
