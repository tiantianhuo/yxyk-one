package com.yxyk.utils;

import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;

import java.util.ArrayList;
import java.util.List;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/27
 * Time：16:09
 */
public class RoChangeUtils {

    public static List<VoNavigation> changToRoNavigation(List<Navigation> navigationList) {
        List<VoNavigation> list = new ArrayList();
        for (Navigation navigation : navigationList) {
            VoNavigation roNavigation = new VoNavigation();
            roNavigation.setPermissionCode(navigation.getPermissionCode());
            roNavigation.setNavigationName(navigation.getNavigationName());
            roNavigation.setPId(navigation.getPId());
            roNavigation.setSort(navigation.getSort());
        }
        return null;
    }
}
