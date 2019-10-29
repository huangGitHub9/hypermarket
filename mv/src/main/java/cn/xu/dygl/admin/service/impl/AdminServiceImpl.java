package cn.xu.dygl.admin.service.impl;

import cn.xu.dygl.admin.dao.AdminDao;
import cn.xu.dygl.admin.entity.Admin;
import cn.xu.dygl.admin.exception.AdminException;
import cn.xu.dygl.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public Admin findAdminByNameAndPassword(Admin admin)throws AdminException {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("adminName",admin.getAdminName());
        params.put("adminPassword",admin.getAdminPassword());

        Admin _admin  = adminDao.findAdminByNameAndPassword(params);
        if(_admin == null){
            throw new AdminException("管理员名或者密码错误！");
        }

        return _admin;
    }

    @Override
    public Map<String, Object> findAccountInfo() {
        return adminDao.findAccountInfo();
    }
}
