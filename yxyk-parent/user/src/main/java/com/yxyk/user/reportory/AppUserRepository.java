package com.yxyk.user.reportory;

import com.yxyk.user.bean.po.AppUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author deng
 * @date 2020/6/17 0017
 */

public interface AppUserRepository extends JpaRepository<AppUserInfo, Long> {

    /**
     * 通过手机号和账号状态查询用户信息
     * @param mobile
     *  手机号
     * @param status
     *  状态
     * @return
     *  用户信息
     */
    AppUserInfo findByMobileAndStatus(String mobile, String status);

}
