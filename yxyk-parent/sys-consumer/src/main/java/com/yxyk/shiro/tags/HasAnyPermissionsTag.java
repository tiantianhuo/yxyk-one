package com.yxyk.shiro.tags;

import com.jagregory.shiro.freemarker.PermissionTag;
import com.yxyk.bean.common.SysConst;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * 扩展标签，具有列出权限中的任意一个
 *
 * @author gq
 */
public class HasAnyPermissionsTag extends PermissionTag {

    public HasAnyPermissionsTag() {

    }

    @Override
    protected boolean showTagBody(String permissions) {
        Subject subject = getSubject();
        if (subject != null) {
            return Arrays.stream(permissions.split(SysConst.SPLIT_TAG)).anyMatch(subject::isPermitted);
        }
        return Boolean.FALSE;
    }

}