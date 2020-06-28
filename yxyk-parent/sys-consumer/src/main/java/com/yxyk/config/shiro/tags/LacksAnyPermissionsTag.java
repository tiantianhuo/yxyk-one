package com.yxyk.config.shiro.tags;

import com.jagregory.shiro.freemarker.PermissionTag;
import com.yxyk.bean.common.SysConst;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * 扩展标签，不包含所有的权限
 *
 * @author gq
 */
public class LacksAnyPermissionsTag extends PermissionTag {

    @Override
    protected boolean showTagBody(String permissions) {
        Subject subject = getSubject();
        if (subject != null) {
            return Arrays.stream(permissions.split(SysConst.SPLIT_TAG)).noneMatch(subject::isPermitted);
        }
        return Boolean.TRUE;
    }

}
