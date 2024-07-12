package com.poscodx.jblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.poscodx.jblog.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	// 데이터베이스에서 정보를 가져온 후 UserDetailsImpl에 정보주입
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.getUser(username,UserDetailsImpl.class);
	}

}
