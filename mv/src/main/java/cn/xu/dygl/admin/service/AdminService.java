package cn.xu.dygl.admin.service;

import cn.xu.dygl.admin.entity.Admin;
import cn.xu.dygl.admin.exception.AdminException;

import java.util.Map;

public interface AdminService {

    public Admin findAdminByNameAndPassword(Admin admin)throws AdminException;

    public Map<String,Object> findAccountInfo();
}
