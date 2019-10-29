package cn.xu.dygl.memPackage.dao;

import cn.xu.dygl.memPackage.entity.MemPackage;

import java.util.List;
import java.util.Map;

public interface MemPackageDao {

    public void add(MemPackage form);

    public void delete(String memPackageId);

    public void update(MemPackage form);

    public MemPackage findObjectById(String memPackageId);

    public List<MemPackage> findAll();

    //查找该套餐通过套餐月份和 套餐价格
    public int findMemPByMP(MemPackage form);

    //找该角色除过自己
    public int findMemPAndExcludeSelf(MemPackage form);

    public String findMemPByPrice(double memPackagePrice);
}
