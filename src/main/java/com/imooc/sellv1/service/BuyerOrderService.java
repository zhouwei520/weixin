package com.imooc.sellv1.service;

import com.imooc.sellv1.dto.OrderDTO;

//买家单
public interface BuyerOrderService {

    public OrderDTO findOrderOne(String openid,String orderid);


    public OrderDTO cancelOrder(String openid,String orderid);
}
