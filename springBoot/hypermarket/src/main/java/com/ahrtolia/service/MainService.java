package com.ahrtolia.service;

import com.ahrtolia.entity.News;
import com.ahrtolia.entity.Product;
import com.ahrtolia.entity.Type1;

import java.util.List;

public interface MainService {
    public List<Type1> getAllType1();

    public List<News> getAllNews();

    List<Product> getAllProduct();

    List<Product> getProductByKeyWord(String keyWord);

    List<Product> getProductByType1(Integer type1Id);

    List<Product> getProductByType2(Integer type2Id);

    List<Product> getProductByType3(Integer type3Id);
}
