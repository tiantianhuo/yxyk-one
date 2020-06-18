package com.yxyk.user.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * author: create by ZhangHao
 * Date: 2019/7/24
 * Time: 16:18
 */
public class ZStringUtils {

    /**
     * 请求参数格式转换 aop 接口使用
     *
     * @param parameterNames  parameterNames
     * @param parameterValues parameterValues
     * @return String
     */
    public static String parseParams(String[] parameterNames, Object[] parameterValues) {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("[");
        int length = parameterNames.length;
        for (int i = 0; i < length; i++) {
            String parameterName = parameterNames[i];
            Object parameterValueObj = parameterValues[i];
            if (parameterValueObj == null){
                return "";
            }
            Class<?> parameterValueClazz = parameterValueObj.getClass();
            String parameterValue;
            if (parameterValueClazz.isPrimitive() ||
                    parameterValueClazz == String.class) {
                parameterValue = parameterValueObj.toString();
            } else if (parameterValueObj instanceof Serializable) {
                parameterValue = JSON.toJSONString(parameterValueObj);
            } else {
                parameterValue = parameterValueObj.toString();
            }
            stringBuffer.append(parameterName).append(":").append(parameterValue).append(" ");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
