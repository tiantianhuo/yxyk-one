package com.yxyk.user.config.web;

import com.yxyk.user.controller.common.BaseController;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig extends BaseController implements WebMvcConfigurer {

    /**
     * 默认跳转到登录页面
     *
     * @param registry --
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry --
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/static").addResourceLocations("classpath:/static");
        registry.addResourceHandler("/Document/**").addResourceLocations("file:" + getCurrentPath() + "/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
