package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryTest {

    @Autowired
    private ProductCategoryRepository   productCategoryRepository;


    @Test
    public void savaTest(){
        ProductCategory object =new ProductCategory();
        object.setCategoryName("乐销版本");
        object.setCategoryType(2);
        productCategoryRepository.save(object);

    }

    @Test
    public void findByOneTest(){

        ProductCategory obj=productCategoryRepository.findOne(1);
        System.out.println(obj);
    }

    @Test
    public void  updateTest(){
        ProductCategory obj=productCategoryRepository.findOne(1);
        obj.setCategoryName("女神最爱");
        productCategoryRepository.save(obj);
    }

    @Test
    public void findByCategoryType(){
        List<Integer> list =Arrays.asList(1,2,3);
        List<ProductCategory> obj= productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,obj
        .size());
        System.out.println(obj.size());
    }
}
