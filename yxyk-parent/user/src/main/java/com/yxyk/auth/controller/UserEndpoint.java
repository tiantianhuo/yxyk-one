package com.yxyk.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author deng
 * @date 2020/06/17
 */

@RestController
@Slf4j
public class UserEndpoint {

    @GetMapping("/user")
	public Principal user(Principal user){
    	LOGGER.debug("user = {}", user);
		return user;
    }
}