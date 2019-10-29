package cn.xu.dygl.role.service.impl;

import cn.xu.dygl.privilege.entity.Privilege;
import cn.xu.dygl.role.dao.RoleDao;
import cn.xu.dygl.role.entity.Role;
import cn.xu.dygl.role.exception.RoleException;
import cn.xu.dygl.role.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Transactional
    @Override
    public void add(Role form,String privilegeIds[])throws RoleException {
         /*
        添加角色的同时也要在t_rolep中添加角色对应的权限
         */
        int counts = roleDao.findRoleByName(form.getRoleName());
        if(counts>0){
            throw new RoleException("角色名称不能重复！,请重新添加");
        }
        roleDao.add(form);
        //添加权限
        addRoleP(form.getRoleId(),privilegeIds);
    }



    @Transactional
    @Override
    public void delete(String roleId) {

        roleDao.delete(roleId);
    }
    @Transactional
    @Override
    public void update(Role form,String privilegeIds[])throws RoleException {
        /*
        修改该角色是还要修改该角色的权限，先删除该角色之前的权限然后在增加新的权限
         */
        int counts = roleDao.findRoleAndExcludeSelf(form);
        if(counts>0){
            throw new RoleException("角色名称不能重复！,请重新修改");
        }
        roleDao.update(form);

        //先老的删除老的权限
        Role role = roleDao.findObjectById(form.getRoleId());
        String oldPrivilegeIds[] = getPrivilegeIds(role.getPrivilegeList());
        delRoleP(form.getRoleId(),oldPrivilegeIds);
        //后添加
        addRoleP(form.getRoleId(),privilegeIds);
    }



    @Override
    public Role findObjectById(String roleId) {
        return roleDao.findObjectById(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
    //添加角色与权限的map封装
    private void addRoleP(String roleId, String[] privilegeIds) {
        Map<String,Object> params = new HashMap<String,Object>();
        for(String pid : privilegeIds){
            params.put("roleId",roleId);
            params.put("privilegeId",pid);
            roleDao.addRoleP(params);
        }
    }

    //删除角色与权限的map封装
    private void delRoleP(String roleId, String[] privilegeIds) {
        Map<String,Object> params = new HashMap<String,Object>();
        for(String pid : privilegeIds){
            params.put("roleId",roleId);
            params.put("privilegeId",pid);
            roleDao.delPByRole(params);
        }
    }
    //得到该权限ids
    private String[] getPrivilegeIds(List<Privilege> privilegeList) {
        String[] ps = new String[privilegeList.size()];
        for(int i=0;i<privilegeList.size();i++){
            ps[i] = privilegeList.get(i).getPrivilegeId();
        }
        return ps;
    }
}
