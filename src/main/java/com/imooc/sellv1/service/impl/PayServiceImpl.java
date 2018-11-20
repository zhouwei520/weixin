package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.service.PayService;
import com.imooc.sellv1.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 支付实现类
 *
 * @author mac
 * @create 2018-11-19 9:14 PM
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;


    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest    payRequest=new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName("微信点餐订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[微信支付] request={}",JsonUtil.toJson(payRequest));

        PayResponse payResponse=bestPayService.pay(payRequest);
        log.info("{微信支付} response={}",JsonUtil.toJson(payResponse));

    }
}