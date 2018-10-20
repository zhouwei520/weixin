package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final  String OPENID="4444444";

    @Test
    public void findByBuyerOpenId() {

        PageRequest request =new PageRequest(0,3);
        Page<OrderMaster>result= repository.findByBuyerOpenid(OPENID,request);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());

    }

    @Test
    public void savaTestOrderMaster(){
        OrderMaster object=new OrderMaster();
        object.setOrderId("1234567");
        object.setBuyerName("师姐");
        object.setBuyerPhone("123456789");
        object.setBuyerAddress("慕课网");
        object.setOrderAmount(new BigDecimal(2.8));
        object.setBuyerOpenid("");
        object.setPayStatus(0);
        object.setOrderStatus(0);
        OrderMaster result= repository.save(object);
        Assert.assertNotNull(result);
    }

}