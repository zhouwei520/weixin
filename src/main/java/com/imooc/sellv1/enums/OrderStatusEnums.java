package com.imooc.sellv1.enums;

import lombok.Getter;

/**
 * 描述:
 * 订单状态枚举
 *
 * @author mac
 * @create 2018-10-04 上午10:24
 */
@Getter
public enum  OrderStatusEnums {
       NEW(0,"新订单"),
    FINISH(1,"老订单"),
       CANCEL(2,"已经完结订单");

       private Integer code;
       private String  message;

    OrderStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}