package com.poscodx.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void join(UserVo vo) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));	
		int result = userRepository.insert(vo);
		if(result==1) {
			result = userRepository.makeBlog(vo);
		}
		if(result==1) {
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setId(vo.getId());
			result = userRepository.setUpCategory(categoryVo);
			System.out.println("new categoryVo: "+categoryVo);
			if(result==1) {
				userRepository.setUpPost(categoryVo.getNo());
			}
		}	
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.getUser(id,password);
	}
	public UserVo getUser(String id) {
		return userRepository.getUser(id);
	}
}
