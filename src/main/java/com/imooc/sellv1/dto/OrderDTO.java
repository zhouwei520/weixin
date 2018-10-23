package com.imooc.sellv1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sellv1.dataobject.OrderDetail;
import com.imooc.sellv1.enums.OrderStatusEnums;
import com.imooc.sellv1.enums.PayStatusEnum;
import com.imooc.sellv1.utils.serializer.Date2LongSerializer;
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
//表示 这个VO中有字段为null 则不返回给前台
/*@JsonInclude(JsonInclude.Include.NON_NULL) */
//如果全部要这样配置的话 太过于麻烦 只需要配置文件配置全局的
// jackson:
//    default-property-inclusion: non_null

public class OrderDTO {


        private  String  orderId;

        private  String  buyerName;       //买家的名字

        private  String  buyerPhone;     //买家电话

        private  String  buyerAddress;   //买家地址

        private  String  buyerOpenid;    //买家openid

        private BigDecimal orderAmount; //订单总金额

        private  Integer  orderStatus ; //订单状态 0 默认新下单

        private  Integer  payStatus ;  //支付状态   默认0 未支付

        @JsonSerialize(using = Date2LongSerializer.class)
        private Date createTime; // 创建时间

        //把数据默认转换为秒单的时间
        @JsonSerialize(using = Date2LongSerializer.class)
        private   Date    updateTime; //修改时间



       private List<OrderDetail>  orderDetailsList;

}