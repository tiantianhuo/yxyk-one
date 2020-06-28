package com.yxyk.config.aspet;

import com.alibaba.fastjson.JSONObject;
import com.yxyk.utils.ZStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 切点
 * Created with IntelliJ IDEA.
 *
 * @author: create by ZhangHao
 * Date: 2019/7/24
 * Time: 15:19
 */
@Slf4j
public abstract class BaseHttpAspect {

    // 方法名
    private static String methodName;
    //参数信息
    private static String paramVal;
    // 开始时间
    private static long startTime;

    protected abstract void aopPointCut();

    @Before("aopPointCut()")
    private void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        //目标方法参数
        Object[] parameterValues = joinPoint.getArgs();
        String[] parameterNames = methodSignature.getParameterNames();

        paramVal = ZStringUtils.parseParams(parameterNames, parameterValues);
        startTime = System.currentTimeMillis();
    }

    @After("aopPointCut()")
    private void doAfter() {
        long E_time = System.currentTimeMillis() - startTime;
        log.info("==============================================================================");
        log.info("执行 {} 耗时为：{}ms", methodName, E_time);
        if (paramVal.length() < 1000) {
            log.info("参数信息：{}", paramVal);
        }
    }

    @AfterReturning(returning = "object", pointcut = "aopPointCut()")
    private void doAfterReturning(Object object) {
        if (object != null) {
            JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(object));
            result.remove("data");
            log.info("response：{}", result.toJSONString());
            log.info("==============================================================================");
        }
    }


}
