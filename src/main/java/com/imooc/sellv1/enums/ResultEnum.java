package com.imooc.sellv1.enums;

import lombok.Getter;

/**
 * 描述:
 * 返回前端消息
 *
 * @author mac
 * @create 2018-10-10 下午9:15
 */
@Getter
public enum  ResultEnum {

    PARAM_ERROR(2,"参数不正确"),
    PARAM_OBJECT_TYPE_ERROR(3,"对象转换失败"),
    PARAM_VO_SELECT_ERROR(4,"查询结果出现异常"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不存在"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情表不存在"),
    ORDER_STATUS_ERROR(14,"订单状态有误"),
    ORDER_UPDATE_STATUS(15,"更新订单状态有问题"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17,"订单表中支付状态有问题"),
    ORDER_MASTER_ERROR(18,"这个订单不是买家的")
    ;
    private Integer code;
    private String  message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResultEnum(String message){
        this.message = message;
    }
}