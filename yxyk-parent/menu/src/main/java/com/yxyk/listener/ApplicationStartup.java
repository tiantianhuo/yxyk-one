package com.yxyk.listener;

import com.yxyk.bean.common.SysConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/28
 * Time：11:37
 */
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        log.info(String.join(SysConst.SPLIT_TAG, context.getEnvironment().getActiveProfiles()));
        log.info("========================启动成功==========================");
    }
}
