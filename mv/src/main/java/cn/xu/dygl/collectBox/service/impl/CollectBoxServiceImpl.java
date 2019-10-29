package cn.xu.dygl.collectBox.service.impl;

import cn.xu.dygl.collectBox.dao.CollectBoxDao;
import cn.xu.dygl.collectBox.entity.CollectBox;
import cn.xu.dygl.collectBox.service.CollectBoxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "collectBoxService")
public class CollectBoxServiceImpl implements CollectBoxService {


    @Resource
    private CollectBoxDao collectBoxDao;

    @Override
    public List<CollectBox> findAll() {
        return collectBoxDao.findAll();
    }

    @Transactional
    public void delete(String collectBoxId) {
        collectBoxDao.delete(collectBoxId);
    }

    @Override
    public CollectBox findObjectById(String collectBoxId) {
        return collectBoxDao.findObjectById(collectBoxId);
    }


}
