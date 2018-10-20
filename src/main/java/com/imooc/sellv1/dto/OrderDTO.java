package com.imooc.sellv1.dto;

import com.imooc.sellv1.dataobject.OrderDetail;
import com.imooc.sellv1.enums.OrderStatusEnums;
import com.imooc.sellv1.enums.PayStatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 订单包装类
 *
 * @author mac
 * @create 2018-10-10 下午8:26
 */
@Data
@Slf4j
public class OrderDTO {


        private  String  orderId;

        private  String  buyerName;       //买家的名字

        private  String  buyerPhone;     //买家电话

        private  String  buyerAddress;   //买家地址

        private  String  buyerOpenid;    //买家openid

        private BigDecimal orderAmount; //订单总金额

        private  Integer  orderStatus ; //订单状态 0 默认新下单

        private  Integer  payStatus ;  //支付状态   默认0 未支付

        private Date createTime; // 创建时间

        private   Date    updateTime; //修改时间



       private List<OrderDetail>  orderDetailsList;

}