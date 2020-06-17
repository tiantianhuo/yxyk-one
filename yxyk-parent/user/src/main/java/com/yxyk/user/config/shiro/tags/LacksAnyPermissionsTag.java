package com.yxyk.user.config.shiro.tags;//package com.com.yxyk.user.config.shiro.tags;
//
//import com.jagregory.shiro.freemarker.PermissionTag;
//import com.com.yxyk.user.bean.common.SysConst;
//import org.apache.shiro.subject.Subject;
//
//import java.util.Arrays;
//
///**
// * 扩展标签，不包含所有的权限
// *
// * @author gq
// */
//public class LacksAnyPermissionsTag extends PermissionTag {
//
//    @Override
//    protected boolean showTagBody(String permissions) {
//        Subject subject = getSubject();
//        if (subject != null) {
//            return Arrays.stream(permissions.split(SysConst.SPLIT_TAG)).noneMatch(subject::isPermitted);
//        }
//        return Boolean.TRUE;
//    }
//
//}
