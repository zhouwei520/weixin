package com.imooc.sellv1.service;

import com.imooc.sellv1.dto.OrderDTO;

/***
 *
 * 支付
 *
 * */
public interface PayService {

   void  create(OrderDTO orderDTO);
}
