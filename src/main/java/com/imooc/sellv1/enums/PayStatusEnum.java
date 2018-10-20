package com.imooc.sellv1.enums;

import lombok.Getter;

/**
 * 描述:
 * 支付枚举
 *
 * @author mac
 * @create 2018-10-08 下午8:00
 */
@Getter
public enum  PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String  message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}