package com.poscodx.jblog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.poscodx.jblog.vo.UserVo;

// 데이터베이스에서 가쟈온 UserVo와 UserDetails를 구현한 SpringSecurity가 아이디와 비밀번호를 비교한다 
public class UserDetailsImpl extends UserVo implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	@Override
	public String getUsername() {
		return getId();
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
