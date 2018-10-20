package com.imooc.sellv1.exception;

import com.imooc.sellv1.enums.ResultEnum;

/**
 * 描述:
 * 自定义异常处理类
 *
 * @author mac
 * @create 2018-10-10 下午9:14
 */
public class SellException extends  RuntimeException {


    private Integer code;

    public SellException(ResultEnum resultEnum){

        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();

    }
}