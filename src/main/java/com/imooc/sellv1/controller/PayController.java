package com.imooc.sellv1.controller;

import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述:
 * 微信支付
 *
 * @author mac
 * @create 2018-11-19 9:00 PM
 */
@RequestMapping
@Controller
@Slf4j
public class PayController {

    @Autowired
    private OrderService  orderService;


    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){
        //1.0查询订单
        OrderDTO orderDTO=orderService.findOne(orderId);
        if (orderDTO==null){
            throw  new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        //发起支付


    }
}