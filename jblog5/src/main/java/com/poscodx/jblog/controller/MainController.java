package com.poscodx.jblog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.vo.UserVo;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index(Authentication authentication, Model model) {
		UserVo authUser=null;
		if(authentication!=null) {
		 authUser = (UserVo)authentication.getPrincipal();
		}
		model.addAttribute("authUser",authUser);
		return "main/index";
	}
}
