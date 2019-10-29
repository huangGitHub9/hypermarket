package cn.xu.dygl.memPackage.service;

import cn.xu.dygl.memPackage.entity.MemPackage;
import cn.xu.dygl.memPackage.exception.MemPackageException;

import java.util.List;

public interface MemPackageService {

    public void add(MemPackage form) throws MemPackageException;

    public void delete(String memPackageId);

    public void update(MemPackage form) throws MemPackageException;

    public MemPackage findObjectById(String memPackageId);

    public List<MemPackage> findAll();
    public String findMemPByPrice(double memPackagePrice);
}
