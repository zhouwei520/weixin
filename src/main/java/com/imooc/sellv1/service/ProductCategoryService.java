package com.imooc.sellv1.service;

import com.imooc.sellv1.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

     ProductCategory findOne(Integer id);

     List<ProductCategory> findAll();

    void sava(ProductCategory productCategory);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

}
