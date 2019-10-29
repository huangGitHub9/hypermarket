package com.ahrtolia.service.impl;

import com.ahrtolia.dao.MainMapper;
import com.ahrtolia.entity.News;
import com.ahrtolia.entity.Product;
import com.ahrtolia.entity.Type1;
import com.ahrtolia.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private MainMapper mainMapper;

    @Override
    public List<Type1> getAllType1() {
        return mainMapper.getAllType1();
    }

    @Override
    public List<News> getAllNews() {
        return mainMapper.getAllNews();
    }

    @Override
    public List<Product> getAllProduct() {
        return mainMapper.getAllProduct();
    }

    @Override
    public List<Product> getProductByKeyWord(String keyWord) {
        return mainMapper.getProductByKeyWord(keyWord);
    }

    @Override
    public List<Product> getProductByType1(Integer type1Id) {
        return mainMapper.getProductByType1(type1Id);
    }

    @Override
    public List<Product> getProductByType2(Integer type2Id) {
        return mainMapper.getProductByType2(type2Id);
    }

    @Override
    public List<Product> getProductByType3(Integer type3Id) {
        return mainMapper.getProductByType3(type3Id);
    }


}
