package com.haiwan.admin.dao;

import com.haiwan.admin.entity.Admin;

import java.sql.SQLException;
import java.util.Map;

public interface AdminDao {
	
	public Admin findByAdminnameAndAdminpwd(Map<String, Object> map) throws SQLException;
}
