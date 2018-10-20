package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.OrderDetail;
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
@Slf4j
@SpringBootTest
public class OrderDetailRepositoryTest {


    @Autowired
    private OrderDetailRepository   repository;

    @Test
    public void findByOrderId() {

       List<OrderDetail> orderDetail=repository.findByOrderId("11111111");
       System.out.println(orderDetail.size());
       Assert.assertNotEquals(0,orderDetail.size());

    }

    @Test
    public  void  savaTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderId("11111111");
        orderDetail.setDetailId("11111111");
        orderDetail.setProductIcon("http://xx.jpg");
        orderDetail.setProductId("111111111");
        orderDetail.setProductQuantity(53);
        orderDetail.setProductName("瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(2.3));
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(result);

    }
}