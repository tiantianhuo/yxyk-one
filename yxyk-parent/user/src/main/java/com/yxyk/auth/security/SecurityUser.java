package com.yxyk.auth.security;

import com.yxyk.user.bean.po.AppUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Deng
 * @date 2019/7/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityUser extends AppUserInfo implements UserDetails, Serializable{

	public SecurityUser(AppUserInfo user){
		if(user != null){
			this.setId(user.getId());
			this.setPassword(user.getPassword());
			this.setSex(user.getSex());
			this.setStatus(user.getStatus());
			this.setDeviceId(user.getDeviceId());
			this.setMobile(user.getMobile());
			this.setHeadImg(user.getHeadImg());
			this.setNickName(user.getNickName());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getMobile() + super.getId().toString() + super.getNickName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
