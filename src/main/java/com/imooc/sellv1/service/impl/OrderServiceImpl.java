package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.converter.OrderMaster2OrderDTOConverter;
import com.imooc.sellv1.dataobject.OrderDetail;
import com.imooc.sellv1.dataobject.OrderMaster;
import com.imooc.sellv1.dataobject.ProductInfo;
import com.imooc.sellv1.dto.CartDTO;
import com.imooc.sellv1.dto.OrderDTO;
import com.imooc.sellv1.enums.OrderStatusEnums;
import com.imooc.sellv1.enums.PayStatusEnum;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.repository.OrderDetailRepository;
import com.imooc.sellv1.repository.OrderMasterRepository;
import com.imooc.sellv1.service.OrderService;
import com.imooc.sellv1.service.ProductInfoService;
import com.imooc.sellv1.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * 订单表实现类
 *
 * @author mac
 * @create 2018-10-10 下午8:55
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService  productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository  orderMasterRepository;


    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId=KeyUtil.genUniqueKey();

        BigDecimal  orderAmount=new BigDecimal(BigInteger.ZERO);

        //1.查询商品 数量  价格
        for (OrderDetail orderDetail:orderDTO.getOrderDetailsList()) {

            ProductInfo productInfo =productInfoService.findOne(orderDetail.getProductId());

             if (productInfo==null){
                 throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
             }
            // 计算订单总价
           orderAmount=productInfo.getProductPrice().multiply(
                    new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
          //  orderDetail.getProductPrice().multiply(
          //   new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount) 一件商品的总价


            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailRepository.save(orderDetail);
        }
        //写入订单数据库
        OrderMaster orderMaster=new OrderMaster();
        //拷贝复制 写入数据库  //如果orderDTO 里面值有null也会拷贝过去  建议放在前面

        //设置订单id和订单总价
        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);

        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOS=orderDTO.getOrderDetailsList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).
                collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

       OrderMaster  master=  orderMasterRepository.findOne(orderId);
       if (master==null){
           //订单不存在
           throw new SellException(ResultEnum.ORDER_NOT_EXIST);
       }
       List<OrderDetail> detailList= orderDetailRepository.findByOrderId(orderId);

       if (CollectionUtils.isEmpty(detailList)){
           //订单详情表不存在
           throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
       }
        //如果商品存在
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(master,orderDTO);
        orderDTO.setOrderDetailsList(detailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

       Page<OrderMaster> orderMasters= orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

       //转换为list
        List<OrderDTO> orderMasterList= OrderMaster2OrderDTOConverter.conver(orderMasters.getContent());

        //改成包装类
        Page<OrderDTO> orderDTO=new PageImpl<OrderDTO>(orderMasterList,pageable,orderMasters.getTotalElements());

        return  orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster   orderMaster=new OrderMaster();

        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error(" [取消订单] 订单状态不正确,orderId={} orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result=orderMasterRepository.save(orderMaster);
        if (result==null){
            log.error("[订单更新失败] result={}",result);
            throw  new SellException(ResultEnum.ORDER_UPDATE_STATUS);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailsList())){
            log.error(" [取消订单] 订单中无商品详情 orderDTO={}",orderDTO);
            throw  new  SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailsList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        //加库存
        productInfoService.increaseStock(cartDTOList);

        //如果已经支付  需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){

        }

        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【完结订单】 订单状态不正确 orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw  new  SellException(ResultEnum.ORDER_STATUS_ERROR);

        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.FINISH.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result =orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("完结订单 更新失败,orderMaster={}",orderMaster);
            throw  new  SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        return orderDTO;
    }

    @Transactional
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("[订单支付完成] 订单状态不正确 orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw  new  SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}",orderDTO);
            throw  new  SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result =orderMasterRepository.save(orderMaster);
        if(result==null){
            log.error("完结支付 更新失败,orderMaster={}",orderMaster);
            throw  new  SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }


        return orderDTO;
    }
}