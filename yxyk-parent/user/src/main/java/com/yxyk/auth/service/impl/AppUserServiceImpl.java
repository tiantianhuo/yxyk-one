package com.yxyk.auth.service.impl;

import com.yxyk.auth.service.AppUserService;
import com.yxyk.user.bean.po.AppUserInfo;
import com.yxyk.user.reportory.AppUserRepository;
import org.springframework.stereotype.Service;

/**
 * @author deng
 * @date 2020/6/17 0017
 */

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository userRepository;

    @Override
    public AppUserInfo findUserByMobileAndStatus(String mobile, int status) {
        return null;
    }
}
