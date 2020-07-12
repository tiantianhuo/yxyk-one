package com.yxyk.utils;

import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.po.Role;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.bean.vo.VoRole;
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
    /**
     * 角色
     *
     * @param voRole 角色vo
     * @return Role
     */
    public static Role changeToRole(VoRole voRole) {
        Role role = new Role();
        role.setDeleteState(SysConst.DeletedState.UN_DELETE_STATE.getCode());
        role.setRoleName(voRole.getName());
        role.setPermissions(voRole.getPermission());
        role.setRemarks(voRole.getRemarks());
        if(voRole.getId()!=null){
            role.setId(voRole.getId());
        }
        return role;
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
}
