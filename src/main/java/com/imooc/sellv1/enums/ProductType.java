package com.imooc.sellv1.enums;

import lombok.Getter;

@Getter
public enum ProductType {

    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String msseage;

    ProductType(Integer code, String msseage) {
        this.code = code;
        this.msseage = msseage;
    }
}
