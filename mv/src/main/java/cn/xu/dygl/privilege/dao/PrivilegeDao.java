package cn.xu.dygl.privilege.dao;

import cn.xu.dygl.privilege.entity.Privilege;

import java.util.List;
import java.util.Map;

public interface PrivilegeDao {

    public void add(Privilege form);

    public void delete(String privilegeId);

    public void update(Privilege form);

    public Privilege findObjectById(String privilegeId);

    public List<Privilege> findAll();


    public int findPriByNameAndAs(Privilege form);

    public int findPriAndExcludeSelf(Privilege form);

}
