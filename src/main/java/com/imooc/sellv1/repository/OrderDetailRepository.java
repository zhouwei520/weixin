package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 *
 *
 * @author mac
 * @create 2018-10-08 下午8:32
 */
public interface  OrderDetailRepository  extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId (String orderId);
}