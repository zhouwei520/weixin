package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {



    @Autowired
    private ProductInfoRepository  productInfoRepository;


    @Test
    public  void savaTest(){

        ProductInfo vo=new ProductInfo();
        vo.setProductId("1");
        vo.setProductName("血鸭");
        vo.setProductPrice(new BigDecimal(15.8));
        vo.setProductStock(100);
        vo.setProductType(0);
        vo.setProductIcon("http://www.baidu.com");
        vo.setProductDescription("永州血鸭");
        vo.setCategoryType(1);
        productInfoRepository.save(vo);

    }



    @Test
    public void findByProductType() {
     List<ProductInfo> list=productInfoRepository.findByProductType(0);
        Assert.assertNotEquals(0,list.size());
    }
}