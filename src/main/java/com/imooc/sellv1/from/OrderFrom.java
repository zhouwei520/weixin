package com.imooc.sellv1.from;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 描述:
 * 前台传入的表单
 *
 * @author mac
 * @create 2018-10-21 下午7:49
 */
@Data
public class OrderFrom {

    @NotEmpty(message = "买家姓名必填")
    private String  name;

    @NotEmpty(message = "买家电话号码必填")
    private String  phone;

    @NotEmpty(message = "买家的OPENID必填")
    private String  openid;

    @NotEmpty(message = "买家的地址必填")
    private  String address;

    private String  items;

}