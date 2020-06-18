package com.yxyk.utils;


import com.yxyk.bean.po.AdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitive;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by zhaokai
 * Date: 2020/6/16
 * Time：11:49
 */
public class ChangeUtils {

    public static AdminSensitive changeToSen(VoAdminSensitive voSensitive) {
        AdminSensitive adminSensitive = new AdminSensitive();
        adminSensitive.setSensitiveword(voSensitive.getSensitiveword());
        adminSensitive.setCreatePerson(voSensitive.getCreatePerson());
        adminSensitive.setId(voSensitive.getId());
        return adminSensitive;
    }
}
