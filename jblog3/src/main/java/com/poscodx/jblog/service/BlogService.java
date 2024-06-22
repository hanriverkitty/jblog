package com.poscodx.jblog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

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
	
	public CategoryVo findCategory(String id,int no) {
		return blogRepository.findCategory(id, no);
	}

	@Transactional
	public List<PostVo> getDefault(String id) {
		List<PostVo> postVo = new ArrayList<PostVo>();
		CategoryVo vo = blogRepository.getDefaultCategory(id);
		System.out.println("blog Service "+vo);
		if(vo!=null) {
			postVo =  blogRepository.getPostByCategoryNo(vo.getNo(), id);
		}
		System.out.println("blog Service postVo"+ postVo);
		return postVo;
	}
	
	public List<PostVo> getPostsByCategory(int categoryNo, String id){
			return blogRepository.getPostByCategoryNo(categoryNo, id);
	}
	
	@Transactional
	public List<PostVo> getPostByNoAndCategory(String id, int categoryNo, int postNo) {
		List<PostVo> postList = new ArrayList<PostVo>();
		List<PostVo> postListExceptOne = new ArrayList<PostVo>();
		PostVo vo = new PostVo();
		vo = blogRepository.getPostByNoAndCategory(postNo,categoryNo,id);
		if(vo!=null) {
			postList.add(vo);
			postListExceptOne = blogRepository.getPostsExceptNo(postNo,categoryNo,id);
		}
		if(postListExceptOne!=null) {
			postList.addAll(postListExceptOne);
		}
		return postList;
	}

	public void changeBlogVo(BlogVo blogVo) {
		blogRepository.changeBlogVo(blogVo);
	} 
}
