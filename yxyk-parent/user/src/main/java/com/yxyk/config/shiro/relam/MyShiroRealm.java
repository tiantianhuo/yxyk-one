package com.yxyk.config.shiro.relam;

import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.common.SystemStatusCode;
import com.yxyk.bean.po.User;
import com.yxyk.reportory.RoleRepository;
import com.yxyk.reportory.UserRepository;
import com.yxyk.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:42
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    /**
     * 用户授权
     *
     * @param principalCollection 授权参数
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User nowLoginUser = (User) SecurityUtils.getSubject().getSession().getAttribute(SysConst.SHIRO_USER_SESSION_NAME);
        if (!Objects.equals(nowLoginUser.getRoleId(), 0L)) {
            roleRepository.findById(nowLoginUser.getRoleId()).ifPresent(role -> {
                simpleAuthorizationInfo.addStringPermissions(Arrays.stream(role.getPermissions().split(SysConst.SPLIT_TAG))
                        .collect(Collectors.toList()));
            });
        } else {
            if (StringUtils.isNotBlank(nowLoginUser.getPressStr())) {
                simpleAuthorizationInfo.addStringPermissions(Arrays.stream(nowLoginUser.getPressStr().split(SysConst.SPLIT_TAG))
                        .collect(Collectors.toList()));
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 认证参数
     * @return AuthenticationInfo
     * @throws AuthenticationException 认证失败异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userRepository.findByUserName(token.getUsername()).orElseThrow(() -> new AuthenticationException(SystemStatusCode.USER_NOT_FOUND.getMsg()));
        User adminUser = userRepository.findByIdAndDeleteState(user.getParentId(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
        if (Objects.isNull(adminUser)) {
            throw new AuthenticationException("帐号或密码错误，请重新输入!");
        } else {
            LocalDateTime startTime = adminUser.getStartTime();
            LocalDateTime endTime = adminUser.getEndTime();
            if (!DateUtils.intervalTime(startTime, endTime, LocalDateTime.now())) {
                throw new AuthenticationException("用户已经到期,请联系管理员!");
            }
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(SysConst.SHIRO_USER_SESSION_NAME, user);
        session.setTimeout(SysConst.SHIRO_SESSION_TIMEOUT);
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
