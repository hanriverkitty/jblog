package com.poscodx.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;

@Controller
// assets를 제외한 모든 문자
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(
			@PathVariable("id") String id,
			Model model
	
			 )
//			@PathVariable("categoryNo") Long categoryNo,
//			@PathVariable("postNo") Long postN) 
			{
		BlogVo vo = blogService.getBlogVo(id);
		System.out.println(vo);
		List<CategoryVo> categoryVo = blogService.getCategory(id);
		model.addAttribute("blogVo",vo);
		model.addAttribute("list",categoryVo);
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
