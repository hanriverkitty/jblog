package com.poscodx.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
// assets를 제외한 모든 문자
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Integer> pathNo1,
			@PathVariable("pathNo2") Optional<Integer> pathNo2,
			Model model
			){
		int categoryNo = 0;
		int postNo = 0;
		System.out.println("path::::::::"+pathNo1+"     "+pathNo2);
		List<PostVo> postList = new ArrayList<PostVo>();
		
		if(pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		System.out.println("Controller ::::::categoryNo::::::  "+categoryNo+"   postNo:::::"+postNo);
		// 기본카테고리 기본글로 이동
		if (categoryNo==0 && postNo==0) {
			postList = blogService.getDefault(id);
			if(postList.size()!=0) {
			categoryNo = postList.get(0).getCategoryNo();
			postNo = postList.get(0).getNo();
			}
		} else if(categoryNo!=0 && postNo==0) {
			postList = blogService.getPostsByCategory(categoryNo, id);
			List<CategoryVo> category = blogService.getCategory(id);
			if(category.size()!=0 && postList.size()==0) {
				BlogVo blogVo = blogService.getBlogVo(id);
				List<CategoryVo> categoryList = blogService.getCategory(id);
				model.addAttribute("blogVo",blogVo);
				model.addAttribute("categoryList",categoryList);
				model.addAttribute("postList",postList);
				return "blog/main";
			}

		} else {
			postList = blogService.getPostByNoAndCategory(id, categoryNo, postNo);
		}
		
		// 해당 블로그에 없는 글이나 카테고리 일 경우
		if(postList==null || postList.size()==0) {
			System.out.println("redirect::::::::::::::");
			return "redirect:/"+id;
		}
		
		BlogVo blogVo = blogService.getBlogVo(id);
		List<CategoryVo> categoryList = blogService.getCategory(id);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("postList",postList);
		return "blog/main";
	}
	
	// @Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id")String id) {
		return "blog/admin-basic";
	}
	// @Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id")String id) {
		return "blog/admin-category";
	}
	// @Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id")String id) {
		return "blog/admin-write";
	}
}
