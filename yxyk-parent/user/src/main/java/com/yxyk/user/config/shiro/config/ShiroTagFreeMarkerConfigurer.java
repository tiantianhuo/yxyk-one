package com.yxyk.user.config.shiro.config;//package com.com.yxyk.user.config.shiro.config;
//
//import com.jagregory.shiro.freemarker.ShiroTags;
//import com.com.yxyk.user.config.shiro.tags.LacksAnyPermissionsTag;
//import com.com.yxyk.user.config.shiro.tags.HasAnyPermissionsTag;
//import freemarker.template.Configuration;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
///**
// * 使FreeMarker支持Shiro标签
// */
//@Component
//public class ShiroTagFreeMarkerConfigurer implements InitializingBean {
//
//    @Autowired
//    private Configuration configuration;
//
//    @Autowired
//    private FreeMarkerViewResolver resolver;
//
//    @Override
//    public void afterPropertiesSet() {
//        // 加上这句后，可以在页面上使用shiro标签
//        ShiroTags tags = new ShiroTags();
//        tags.put("hasAnyPermissions", new HasAnyPermissionsTag());
//        tags.put("lacksAnyPermissions", new LacksAnyPermissionsTag());
//        configuration.setSharedVariable("shiro", tags);
//        // 加上这句后，可以在页面上用${context.contextPath}获取contextPath
//        resolver.setRequestContextAttribute("context");
//    }
//}