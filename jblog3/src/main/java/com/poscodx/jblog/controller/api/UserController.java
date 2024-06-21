package com.poscodx.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscodx.jblog.dto.JsonResult;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@RestController("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid")
	public JsonResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo vo = userService.getUser(id);
		System.out.println(vo);
		return JsonResult.success(vo != null); 
	}
}