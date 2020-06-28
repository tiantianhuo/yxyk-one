package com.yxyk.utils;

import com.yxyk.bean.po.Banner;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.bean.vo.VoUser;

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
        navigation.setPermissionId(voNavigation.getPermissionId());
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
}
