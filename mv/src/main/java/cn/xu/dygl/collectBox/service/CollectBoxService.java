package cn.xu.dygl.collectBox.service;

import cn.xu.dygl.collectBox.entity.CollectBox;

import java.util.List;

public interface CollectBoxService {

    public List<CollectBox> findAll();

    public void delete(String collectBoxId);

    public CollectBox findObjectById(String collectBoxId);
}
