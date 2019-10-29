package cn.xu.dygl.memPackage.service.impl;

import cn.xu.dygl.memPackage.dao.MemPackageDao;
import cn.xu.dygl.memPackage.entity.MemPackage;
import cn.xu.dygl.memPackage.exception.MemPackageException;
import cn.xu.dygl.memPackage.service.MemPackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "memPackageService")
public class MemPackageServiceImpl implements MemPackageService {

    @Resource
    private MemPackageDao memPackageDao;

    @Transactional
    @Override
    public void add(MemPackage form)throws MemPackageException {
        int counts = memPackageDao.findMemPByMP(form);
        if(counts >0){
            throw new MemPackageException("套餐价格和套餐月份已存在，请重新添加");
        }
        memPackageDao.add(form);
    }

    @Transactional
    @Override
    public void delete(String memPackageId) {
        memPackageDao.delete(memPackageId);
    }

    @Transactional
    @Override
    public void update(MemPackage form)throws MemPackageException {
        int counts = memPackageDao.findMemPAndExcludeSelf(form);
        if(counts >0){
            throw new MemPackageException("套餐价格和套餐月份已存在，请重新修改");
        }

        memPackageDao.update(form);
    }

    @Override
    public MemPackage findObjectById(String memPackageId) {
        return memPackageDao.findObjectById(memPackageId);
    }

    @Override
    public List<MemPackage> findAll() {
        return memPackageDao.findAll();
    }

    @Override
    public String findMemPByPrice(double memPackagePrice) {
        return memPackageDao.findMemPByPrice(memPackagePrice);
    }
}
