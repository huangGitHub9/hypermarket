package cn.xu.dygl.privilege.service;

import cn.xu.dygl.privilege.entity.Privilege;
import cn.xu.dygl.privilege.exception.PrivilegeException;

import java.util.List;

public interface PrivilegeService {

    public void add(Privilege form) throws PrivilegeException;

    public void delete(String privilegeId);

    public void update(Privilege form) throws PrivilegeException;

    public Privilege findObjectById(String privilegeId);

    public List<Privilege> findAll();
}
