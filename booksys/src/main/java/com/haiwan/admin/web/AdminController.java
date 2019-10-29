package com.haiwan.admin.web;

import com.haiwan.admin.entity.Admin;
import com.haiwan.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/admin")
public class AdminController{
	
	@Resource
	private AdminService adminService;
	
	@RequestMapping("/quit.do")
	public String quit(HttpServletRequest request){
		request.getSession().invalidate();
		return "adminjsps/login";
	}
	
	@RequestMapping("/login.do")
	public String login(ModelMap map, @RequestParam("adminname") String adminname,@RequestParam("adminpwd") String adminpwd, HttpSession session){
		if(adminname==null || adminname.trim().isEmpty()){
			map.addAttribute("msg", "管理员账户不能为空！");
			return "adminjsps/login";
		}else if(adminpwd==null || adminpwd.trim().isEmpty()){
			map.addAttribute("msg", "密码不能为空！");
			map.addAttribute("adminname", adminname);
			return "adminjsps/login";
		}
		Admin admin = adminService.findByAdminnameAndAdminpwd(adminname, adminpwd);
		if( admin == null){
			map.addAttribute("msg", "管理员账户或密码错误");
			map.addAttribute("admin", admin);
			return "adminjsps/login";
		}else{
			session.setAttribute("sessionAdmin", admin);
			return "adminjsps/admin/index";
		}
	}

}
