package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dataobject.OrderDetail;
import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.OrderStatusEnums;
import com.imooc.sellv1.enums.PayStatusEnum;
import com.imooc.sellv1.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {




    @Autowired
    private OrderServiceImpl orderService;

    private final  String OPENID="110";

    private final  String OrderID="1539507890540787463";

    @Test
    public void create() {

        //构建对象
        OrderDTO  orderDTO=new OrderDTO();
        orderDTO.setBuyerName("辽师");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("15992806745");
        orderDTO.setBuyerOpenid(OPENID);
        //购物车
        List<OrderDetail> list=new ArrayList<OrderDetail>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("3");
        orderDetail.setProductQuantity(1);
        list.add(orderDetail);

        orderDTO.setOrderDetailsList(list);

        OrderDTO object=orderService.create(orderDTO);
        log.info("{创建订单} result={}",object);
    }

    @Test
    public void findOne() {

        OrderDTO orderDTO=orderService.findOne("1539507890540787463");
        log.info("[查询出来的结果集] orderDTO={}" ,orderDTO);

        Assert.assertEquals("1539507890540787463",orderDTO.getOrderId());
    }

    @Test
    public void findList() {

        Pageable  pageable=new PageRequest(0,4);
        Page<OrderDTO>orderDTOS= orderService.findList(OPENID,pageable);
        log.info("[OrderDTO] 描述类 result={}",orderDTOS.getContent());

    }

    @Test
    public void cancel() {
      OrderDTO  orderDTO =orderService.findOne(OrderID);
      OrderDTO result=orderService.cancel(orderDTO);
      Assert.assertEquals(OrderStatusEnums.CANCEL.getCode(),result.getOrderStatus());
    }


    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne(OrderID);
        OrderDTO result=orderService.finish(orderDTO);
        log.info("数据是 result={}",result);
        Assert.assertEquals(OrderStatusEnums.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {

        OrderDTO orderDTO=orderService.findOne(OrderID);
        OrderDTO result=orderService.paid(orderDTO);
        log.info("支付结果 result={}",result);
        Assert.assertEquals(result.getPayStatus(),PayStatusEnum.SUCCESS.getCode());
    }
}