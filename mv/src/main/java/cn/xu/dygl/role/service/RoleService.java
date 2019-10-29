package cn.xu.dygl.role.service;

import cn.xu.dygl.role.entity.Role;
import cn.xu.dygl.role.exception.RoleException;

import java.util.List;

public interface RoleService {

    public void add(Role form, String privilegeIds[]) throws RoleException;

    public void delete(String roleId);

    public void update(Role form, String privilegeIds[]) throws RoleException;

    public Role findObjectById(String roleId);

    public List<Role> findAll();
}
