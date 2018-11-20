package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.service.BuyerOrderService;
import com.imooc.sellv1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 买家单实现类
 *
 * @author mac
 * @create 2018-10-24 下午8:33
 */
@Slf4j
@Service
public class BuyerOrderServiceImpl implements BuyerOrderService {



    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return findOrderMaster(openid,orderid);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {

        OrderDTO orderDTO=findOrderMaster(openid,orderid);
        if (orderDTO==null){
            log.error("改数据不正确");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO=orderService.cancel(orderDTO);
        return orderDTO;
    }

    public OrderDTO findOrderMaster(String openid,String orderid){

       OrderDTO orderDTO= orderService.findOne(orderid);
       if (orderDTO==null){
           return  null ;
       }
       if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
           log.error("这个订单信息不是他本人的");
           throw  new SellException(ResultEnum.ORDER_MASTER_ERROR);
       }
       return orderDTO;


    }

}