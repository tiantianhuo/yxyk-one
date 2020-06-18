package com.yxyk.user.config.aop;

import com.yxyk.user.config.aspet.BaseHttpAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/8
 * Time：11:18
 */
@Aspect
@Component
public class HttpAop extends BaseHttpAspect {

    @Override
    @Pointcut("execution( * com.yxyk.user.controller..*.*(..))")
    protected void aopPointCut() {

    }
}
