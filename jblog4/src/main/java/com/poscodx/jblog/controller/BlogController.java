package com.poscodx.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.security.AuthUser;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
// assets를 제외한 모든 문자
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
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
		
		UserVo userVo = userService.getUser(id);
		//해당유저가 존재하지 않을때
		if(userVo==null) {
			return "redirect:/";
		}
		
		System.out.println("Controller ::::::categoryNo::::::  "+categoryNo+"   postNo:::::"+postNo);
		
		// 기본카테고리 기본글로 이동
		if (categoryNo==0 && postNo==0) {
			postList = blogService.getDefault(id);
			if(postList.size()!=0) {
			categoryNo = postList.get(0).getCategoryNo();
			postNo = postList.get(0).getNo();
			}else {
				BlogVo blogVo = blogService.getBlogVo(id);
				List<CategoryVo> categoryList = blogService.getCategory(id);
				model.addAttribute("blogVo",blogVo);
				model.addAttribute("categoryList",categoryList);
				model.addAttribute("postList",postList);
				return "blog/main";
			}
		} else if(categoryNo!=0 && postNo==0) {
			postList = blogService.getPostsByCategory(categoryNo, id);
			CategoryVo category = blogService.findCategory(id,categoryNo);
			System.out.println("category가 있지만 블로그에 없을때:::"+category);
			if(category!=null && postList.size()==0) {
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
	
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id")String id, Model model, @AuthUser UserVo authUser) {
		System.out.println("authUser::::::::"+authUser);
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		BlogVo blogVo = blogService.getBlogVo(id);
		model.addAttribute(blogVo);
		return "blog/admin-basic";
	}
	
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id")String id, BlogVo blogVo, MultipartFile file, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		System.out.println("ADMIN 업데이트");
		String profile = fileUploadService.restore(file);
		if(profile != null) {
			blogVo.setLogo(profile);
		}
		blogService.changeBlogVo(blogVo);
		return "redirect:/"+id;
	}
	
	@Auth
	@RequestMapping(value="/admin/category",method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id")String id,Model model, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		List<CategoryVo> categoryList = blogService.getCategory(id);
		List<Integer> countPostList = new ArrayList<>();
		
		for (CategoryVo vo : categoryList) {
			int count=0;
			count = blogService.getCountPostsByCategoryNo(vo.getNo());
			countPostList.add(count);
			System.out.println("categoryId::::"+vo.getNo()+"  "+"count::::::::::"+count);
		}
		
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("countPostList",countPostList);
		return "blog/admin-category";
	}
	
	@Auth
	@RequestMapping(value="/admin/category",method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id")String id, CategoryVo categoryVo, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		System.out.println(categoryVo);
		blogService.insertCategory(categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(@PathVariable("id")String id, @RequestParam(value="categoryNo") int no, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		System.out.println("categoryNo::::"+no+"삭제");
		blogService.deleteCategory(no);
		return "redirect:/"+id+"/admin/category";
	}
		
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id")String id, Model model, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		List<CategoryVo> categoryList = blogService.getCategory(id);
		model.addAttribute("categoryList",categoryList);
		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id")String id, PostVo vo, @AuthUser UserVo authUser) {
		if(!id.equals(authUser.getId())) {
			return "redirect:/"+id;
		}
		System.out.println(vo);
		blogService.insertPost(vo);
		return "redirect:/"+id+"/"+vo.getCategoryNo()+"/"+vo.getNo();
	}
}
