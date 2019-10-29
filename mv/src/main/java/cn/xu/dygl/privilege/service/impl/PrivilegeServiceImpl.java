package cn.xu.dygl.privilege.service.impl;

import cn.xu.dygl.privilege.dao.PrivilegeDao;
import cn.xu.dygl.privilege.entity.Privilege;
import cn.xu.dygl.privilege.exception.PrivilegeException;
import cn.xu.dygl.privilege.service.PrivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {

    @Resource
    private PrivilegeDao privilegeDao;

    @Transactional
    @Override
    public void add(Privilege form)throws PrivilegeException {
        int counts = privilegeDao.findPriByNameAndAs(form);
        if(counts >0){
            throw new PrivilegeException("权限名或权限别名已注册！请你重新添加");
        }
        privilegeDao.add(form);

    }
    @Transactional
    @Override
    public void delete(String privilegeId) {

        privilegeDao.delete(privilegeId);

    }
    @Transactional
    @Override
    public void update(Privilege form)throws PrivilegeException {
        int counts = privilegeDao.findPriAndExcludeSelf(form);
        if(counts >0){
            throw new PrivilegeException("权限名或权限别名已注册！请重新修改");
        }
        privilegeDao.update(form);
    }

    @Override
    public Privilege findObjectById(String privilegeId) {
        return privilegeDao.findObjectById(privilegeId);
    }

    @Override
    public List<Privilege> findAll() {
        return privilegeDao.findAll();
    }
}
