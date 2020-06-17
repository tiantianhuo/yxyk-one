package com.yxyk.auth.security;

import com.yxyk.auth.service.AppUserService;
import com.yxyk.user.bean.po.AppUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;

/**
 * @author TieYan
 * @date 2018/1/15
 */
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserService appUserService;
//	@Autowired
//	private TOrgUnitMapper tOrgUnitMapper;
//	public MyUserDetailsService(UserDao userDao, AppConfig appConfig) {
//		this.userDao = userDao;
//		this.appConfig = appConfig;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUserInfo user = appUserService.findUserByMobileAndStatus(username, 0);
		if (user == null) {
			throw new UsernameNotFoundException("NOT_FOUND");
		}
		LOGGER.debug("user's details is: {}", user.toString());
		return new SecurityUser(user);
	}

	public static void main(String[] args) {
//		String password = new BCryptPasswordEncoder().encode("skyids");
//		System.out.println(password);
        BigDecimal bvolume = new BigDecimal("0.003").setScale(2, BigDecimal.ROUND_HALF_UP);
        if(bvolume.toString().equals("0.00")){
            System.out.println("123");

        }

    }
}
