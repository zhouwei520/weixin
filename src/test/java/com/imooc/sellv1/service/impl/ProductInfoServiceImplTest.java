package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dataobject.ProductInfo;
import com.imooc.sellv1.enums.ProductType;
import com.imooc.sellv1.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {

    private Logger  logger =LoggerFactory.getLogger(ProductInfoServiceImplTest.class);

    @Autowired
    private ProductInfoService  productInfoService;

    @Test
    public void findOne() {
        ProductInfo vo=productInfoService.findOne("1");
        logger.info("sss"+vo);
    }

    @Test
    public void findByProductType() {
       List<ProductInfo>  objecList= productInfoService.findByProductType();
       logger.info("sss"+(objecList.size()>0?"有":"没有"));
    }

    @Test
    public void findByAll() {

        PageRequest  request =new PageRequest(0,10);
        Page<ProductInfo> list= productInfoService.findByAll(request);
        logger.info("ss"+list.getTotalElements());

    }

    @Test
    public void savaProductInfo() {
        ProductInfo vo=new ProductInfo();
        vo.setCategoryType(2);
        vo.setProductDescription("长沙臭豆腐");
        vo.setProductIcon("www.baidu.com");
        vo.setProductType(1);
        vo.setProductId("2");
        vo.setProductName("长沙臭豆腐");
        vo.setProductPrice(new BigDecimal(4.5));
        vo.setProductStock(50);
        productInfoService.savaProductInfo(vo);
    }

    @Test
    public void updateProductInfo() {
       ProductInfo vo= productInfoService.findOne("2");
       vo.setCategoryType(ProductType.DOWN.getCode());
       productInfoService.updateProductInfo(vo);
    }
}