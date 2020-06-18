package com.yxyk.auth.service;

import com.yxyk.user.bean.po.AppUserInfo;

/**
 * @author deng
 * @date 2020/6/17 0017
 */

public interface AppUserService {

    /**
     * 通过手机号和状态查询用户信息
     * @param mobile
     *  手机号
     * @param status
     *  账号状态
     * @return
     *  用户信息
     */
    AppUserInfo findUserByMobileAndStatus(String mobile, int status);

}
