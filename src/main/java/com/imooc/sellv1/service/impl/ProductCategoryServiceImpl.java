package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dataobject.ProductCategory;
import com.imooc.sellv1.repository.ProductCategoryRepository;
import com.imooc.sellv1.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private Logger  logger =LoggerFactory.getLogger(ProductCategoryServiceImpl.class);


    @Autowired
    private ProductCategoryRepository  productCategoryRepository;


    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryRepository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public void sava(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return productCategoryRepository.findByCategoryTypeIn(list);
    }
}
