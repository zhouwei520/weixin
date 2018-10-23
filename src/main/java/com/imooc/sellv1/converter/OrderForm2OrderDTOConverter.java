package com.imooc.sellv1.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sellv1.dataobject.OrderDetail;
import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.from.OrderFrom;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 转换
 *
 * @author mac
 * @create 2018-10-21 下午8:40
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public  static OrderDTO  convert(OrderFrom orderFrom){

        Gson  gson =new Gson();


        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAddress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail> detailList=new ArrayList<>();
        try {
            detailList=gson.fromJson(orderFrom.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception ex){
            log.error("对象转换失败  ,items={}",orderFrom.getItems());
            throw  new SellException(ResultEnum.PARAM_OBJECT_TYPE_ERROR);
        }
        orderDTO.setOrderDetailsList(detailList);

        return orderDTO;

    }

}