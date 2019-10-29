package cn.xu.dygl.collectBox.dao;

import cn.xu.dygl.collectBox.entity.CollectBox;

import java.util.List;

public interface CollectBoxDao {

    public List<CollectBox> findAll();

    public void delete(String collectBoxId);

    public CollectBox findObjectById(String collectBoxId);

}