package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 订单
 *
 * @author mac
 * @create 2018-10-08 下午8:26
 */
public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String> {

    Page findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}