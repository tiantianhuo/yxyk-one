package com.yxyk.utils;

import com.yxyk.bean.po.*;
import com.yxyk.bean.vo.*;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/27
 * Time：14:49
 */
public class VoChangeUtils {

    public static User changeToUser(VoUser voUser) {
        User user = new User();
        user.setUserName(voUser.getUserName());
        user.setRoleId(voUser.getRoleId());
        user.setPassword(voUser.getPassword());
        user.setId(voUser.getId());
        user.setRemarks(voUser.getRemarks());
        return user;
    }

    public static Navigation changeToNavigation(VoNavigation voNavigation) {
        Navigation navigation = new Navigation();
        navigation.setNavigationName(voNavigation.getNavigationName());
        navigation.setPermissionCode(voNavigation.getPermissionCodes());
        navigation.setPId(voNavigation.getPId());
        navigation.setSort(voNavigation.getSort());
        if(voNavigation.getId()!=null){
            navigation.setId(voNavigation.getId());
        }
        return navigation;
    }

    public static Banner changeToBanner(VoBanner voBanner) {
        Banner banner = new Banner();
        if(voBanner.getId()!=null){
            banner.setId(voBanner.getId());
            banner.setOrderNumber(voBanner.getOrderNumber());
        }else {
            banner.setOrderNumber(System.currentTimeMillis());
        }
        banner.setName(voBanner.getName());
        banner.setNavigationId(voBanner.getNavigationId());
        banner.setOpenState(voBanner.getOpenState());
        banner.setPath(voBanner.getPath());
        return banner;

    }
    public static AdminArticle changeToAr(VoArticle voArticle) {
        AdminArticle adminArticle = new AdminArticle();
        if(voArticle.getId()!=null){
            adminArticle.setId(voArticle.getId());
            adminArticle.setSort(voArticle.getSort());
        }else {
            adminArticle.setSort(System.currentTimeMillis());
        }
        //adminArticle.setSort(voArticle.getSort());
        adminArticle.setChannel(voArticle.getChannel());
        adminArticle.setChannelName(voArticle.getChannelName());
        adminArticle.setTitle(voArticle.getTitle());
        adminArticle.setContent(voArticle.getContent());
        adminArticle.setCreatePerson("USER");
        return adminArticle;
    }
    public static AdminSensitive changeToSen(VoAdminSensitive voAdminSensitive) {
        AdminSensitive adminSensitive = new AdminSensitive();
        if(voAdminSensitive.getId()!=null){
            adminSensitive.setId(voAdminSensitive.getId());
        }
        adminSensitive.setSensitiveword(voAdminSensitive.getSensitiveword());
        adminSensitive.setCreatePerson("USER");
        return adminSensitive;
    }
}
