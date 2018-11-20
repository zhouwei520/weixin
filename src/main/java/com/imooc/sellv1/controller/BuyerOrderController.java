package com.imooc.sellv1.controller;

import com.imooc.sellv1.converter.OrderForm2OrderDTOConverter;
import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.from.OrderFrom;
import com.imooc.sellv1.service.BuyerOrderService;
import com.imooc.sellv1.service.impl.OrderServiceImpl;
import com.imooc.sellv1.utils.ResultVOUtils;
import com.imooc.sellv1.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author mac
 * @create 2018-10-21 下午7:43
 */
@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderServiceImpl  orderService;

    @Autowired
    private BuyerOrderService buyerOrderService;

    @PostMapping("/create")
    //创建订单
    public ResultVO<Map<String,String>> create(@Valid OrderFrom orderFrom ,BindingResult bindingResult) throws  Exception{

        //判断参数是否正确
        if (bindingResult.hasErrors()){
            log.error("[创建订单] 参数不正确 ，orderForm={}",orderFrom);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO=orderService.create(OrderForm2OrderDTOConverter.convert(orderFrom));

        if (orderDTO==null){
            log.error("[创建订单] 返回结果出现异常 ，orderDTO={}",orderDTO);
            throw  new SellException(ResultEnum.PARAM_VO_SELECT_ERROR);

        }
        Map<String,String> map=new HashMap<>();
        map.put("OrderID",orderDTO.getOrderId());

        return ResultVOUtils.success(map);

    }


   @GetMapping("/list")
    //订单列表
   public  ResultVO<List<OrderDTO>> getFindList(@RequestParam("openid") String openid,
                                                @RequestParam(value = "page",defaultValue ="0" ) Integer page,
                                                @RequestParam(value = "size",defaultValue = "10") Integer size){
             if (StringUtils.isEmpty(openid)){
                 log.error("参数错误 openid不能为空");
                 throw new SellException(ResultEnum.PARAM_ERROR);
             }
             PageRequest  pageRequest=new PageRequest(page,size);
             Page<OrderDTO> orderDTOS= orderService.findList(openid,pageRequest);
             return  ResultVOUtils.success(orderDTOS.getContent());
   }
    //订单详情

    @GetMapping("/detail")

    public  ResultVO<OrderDTO> getFindDetail(@RequestParam("openid") String openid,
                                             @RequestParam("orderid") String orderid){
        if (StringUtils.isEmpty(openid)){
            log.error("openid不能为空 必须填写");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderid)){
            log.error("订单id不能为空 必须填写");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }

        //返回结果
        return  ResultVOUtils.success( buyerOrderService.findOrderOne(openid,orderid));
    }


    //取消订单
    @PostMapping("cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid){
        if (StringUtils.isEmpty(openid)){
            log.error("openid不能为空 必须填写");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(orderid)){
            log.error("订单id不能为空 必须填写");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }

       OrderDTO orderDTO=buyerOrderService.cancelOrder(openid,orderid);
        return ResultVOUtils.success(orderDTO);
    }

}