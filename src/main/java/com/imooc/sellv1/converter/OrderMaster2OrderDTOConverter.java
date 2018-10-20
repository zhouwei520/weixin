package com.imooc.sellv1.converter;

import com.imooc.sellv1.dataobject.OrderMaster;
import com.imooc.sellv1.dto.OrderDTO;
import org.springframework.beans.BeanUtils;



import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * 转换器
 *
 * @author mac
 * @create 2018-10-14 下午7:27
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO  orderDTO =new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public  static List<OrderDTO> conver(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map
               (e -> convert(e)
        ).collect(Collectors.toList());
    }
}