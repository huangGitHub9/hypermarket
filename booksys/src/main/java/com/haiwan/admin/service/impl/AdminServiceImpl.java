package com.haiwan.admin.service.impl;

import com.haiwan.admin.dao.AdminDao;
import com.haiwan.admin.entity.Admin;
import com.haiwan.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;
	
	public Admin findByAdminnameAndAdminpwd(String adminname, String adminpwd){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("adminname", adminname);
			map.put("adminpwd", adminpwd);
			return adminDao.findByAdminnameAndAdminpwd(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
