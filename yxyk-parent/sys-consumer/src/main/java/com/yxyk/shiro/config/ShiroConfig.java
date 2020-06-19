package com.yxyk.shiro.config;


import com.yxyk.bean.common.SysConst;
import com.yxyk.shiro.factory.MyShiroFilterFactoryBean;
import com.yxyk.shiro.relam.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：15:12
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(getSessionManage());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
//        cookie.setName(SysConst.COOKIE_SUFFIX + UUID.randomUUID().toString());
        cookie.setName(SysConst.COOKIE_SUFFIX);
        cookie.setMaxAge(1000 * 60 * 60 * 24 * 3);
        return cookie;
    }

    @Bean
    public DefaultWebSessionManager getSessionManage() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setSessionValidationScheduler(executorServiceSessionValidationScheduler());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        EnterpriseCacheSessionDAO cacheSessionDAO = new EnterpriseCacheSessionDAO();
        sessionManager.setSessionDAO(cacheSessionDAO);
        return sessionManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new MyShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/");
        factoryBean.setUnauthorizedUrl("/403");
        loadShiroFilterChain(factoryBean);
        return factoryBean;
    }

    /**
     * 方法名：
     * 功能：凭证匹配器
     * 描述：  指定shiro加密方式和次数
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(SysConst.SHIRO_PASSWORD_TYPE);
        hashedCredentialsMatcher.setHashIterations(SysConst.SHIRO_PASSWORD_COUNT);
        return hashedCredentialsMatcher;
    }

    /**
     * 记住我的cookie管理器
     *
     * @return SimpleCookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(SysConst.SHIRO_REMBERME_COOKIE_ID);
        simpleCookie.setMaxAge(1000 * 60 * 60 * 24 * 3);
        simpleCookie.setHttpOnly(Boolean.TRUE);
        return simpleCookie;
    }

    /**
     * cookie管理器
     *
     * @return CookieRememberMeManager
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        filterChainMap.put("/logout", "anon");  //退出
        filterChainMap.put("/apis/user/loginUser", "anon");//登录请求

//        //小程序接口
//        filterChainMap.put("/banners/findBannerWX", "anon");//查询所有banner
//        filterChainMap.put("/banners/findBannerByIdWX", "anon");//查询一个banner
//        filterChainMap.put("/apis/affiche/findAfficheWX", "anon");//查询所有公告
//        filterChainMap.put("/apis/affiche/findAfficheByPageWX", "anon");//分页查询所有公告
//        filterChainMap.put("/apis/affiche/findAfficheByIdWX", "anon");//查询一个公告
//        filterChainMap.put("/apis/guidance/findOneWX", "anon");//查询举报指引


        filterChainMap.put("/static/**", "anon"); //js资源放行
        filterChainMap.put("/**", "authc");//其余全部拦截

//        filterChainMap.put("/**", "anon");//其余全部拦截
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
    }

}
