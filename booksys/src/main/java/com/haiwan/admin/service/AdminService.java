package com.haiwan.admin.service;


import com.haiwan.admin.entity.Admin;

public interface AdminService {	
	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd);
}
