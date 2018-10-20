package com.imooc.sellv1.dto;

import lombok.Data;

/**
 * 描述:
 * 购物车
 *
 * @author mac
 * @create 2018-10-10 下午9:51
 *
 */
@Data
public class CartDTO {


    private String  productId; //商品ID


    private Integer productQuantity;//商品数量

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}