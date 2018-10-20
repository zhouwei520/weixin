package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dataobject.ProductCategory;
import com.imooc.sellv1.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCategoryServiceImplTest {

    private Logger  logger=LoggerFactory.getLogger(ProductCategoryServiceImplTest.class);

    @Autowired
    private ProductCategoryService   productCategoryService;


    @Test
    public void findOne() {
       ProductCategory productCategory= productCategoryService.findOne(1);
       logger.info("===="+productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list= productCategoryService.findAll();
        for (ProductCategory obj:list) {
            logger.info("===="+obj+"====");
        }

    }

    @Test
    public void sava() {
        ProductCategory obj=new ProductCategory();
        obj.setCategoryName("男神最爱");
        obj.setCategoryType(3);
        productCategoryService.sava(obj);

    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list=Arrays.asList(1,2,3);
        List<ProductCategory> obj=productCategoryService.findByCategoryTypeIn(list);
        for (ProductCategory s:obj) {
            logger.info("=="+s);
        }
    }
}