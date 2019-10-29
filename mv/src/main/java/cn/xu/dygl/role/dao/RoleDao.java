package cn.xu.dygl.role.dao;

import cn.xu.dygl.role.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    public void add(Role form);

    public void delete(String roleId);

    public void update(Role form);

    public Role findObjectById(String roleId);

    public List<Role> findAll();

    //查找该角色通过名字
    public int findRoleByName(String name);

    //找该角色除过自己
    public int findRoleAndExcludeSelf(Role form);

    //添加该角色对应的权限
    public void addRoleP(Map<String, Object> params);

    //删除该角色对应的权限
    public void delPByRole(Map<String, Object> params);






}
