package com.imooc.sellv1.service;

import com.imooc.sellv1.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * 描述:
 * 订单表
 *
 * @author mac
 * @create 2018-10-09 下午10:11
 */
public interface OrderService {

    /**创建订单*/

    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单*/

    OrderDTO findOne(String orderId);

    /**查询订单列表*/
    Page<OrderDTO> findList(String orderId, Pageable pageable);

    /**取消订单*/
    OrderDTO  cancel(OrderDTO orderDTO);

    /**完结订单*/
    OrderDTO  finish(OrderDTO orderDTO);


    /**支付订单*/
    OrderDTO  paid(OrderDTO orderDTO);

}