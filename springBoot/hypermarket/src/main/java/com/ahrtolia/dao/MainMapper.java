package com.ahrtolia.dao;

import com.ahrtolia.entity.News;
import com.ahrtolia.entity.Product;
import com.ahrtolia.entity.Type1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MainMapper {
    public List<Type1> getAllType1();

    public List<News> getAllNews();

    List<Product> getAllProduct();

    List<Product> getProductByKeyWord(@Param("keyWord") String keyWord);

    List<Product> getProductByType1(@Param("type1Id") Integer type1Id);

    List<Product> getProductByType2(@Param("type2Id") Integer type2Id);

    List<Product> getProductByType3(@Param("type3Id") Integer type3Id);
}
