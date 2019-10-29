package cn.xu.dygl.admin.dao;

import cn.xu.dygl.admin.entity.Admin;

import java.util.Map;

public interface AdminDao {

    public Admin findAdminByNameAndPassword(Map<String, Object> params);

    public Map<String,Object> findAccountInfo();
}
