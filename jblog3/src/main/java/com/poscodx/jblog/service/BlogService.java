package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getBlogVo(String id) {
		return blogRepository.getBlogVo(id);
	}

	public List<CategoryVo> getCategory(String id) {
		return blogRepository.getCategory(id);
	}
	
	

}
