package com.yxyk.utils;


import com.yxyk.bean.po.Sensitive;
import com.yxyk.bean.vo.VoSensitive;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：11:49
 */
public class ChangeUtils {

    public static Sensitive changeToSen(VoSensitive voSensitive) {
        Sensitive sensitive = new Sensitive();
        sensitive.setSensitiveword(voSensitive.getSensitiveword());
        sensitive.setCreatePerson(voSensitive.getCreatePerson());
        sensitive.setId(voSensitive.getId());
        return sensitive;
    }
}
