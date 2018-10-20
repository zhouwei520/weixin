package com.imooc.sellv1.service.impl;

import com.imooc.sellv1.dataobject.ProductInfo;
import com.imooc.sellv1.dto.CartDTO;
import com.imooc.sellv1.enums.ProductType;
import com.imooc.sellv1.enums.ResultEnum;
import com.imooc.sellv1.exception.SellException;
import com.imooc.sellv1.repository.ProductInfoRepository;
import com.imooc.sellv1.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {


    private Logger logger =LoggerFactory.getLogger(ProductInfoServiceImpl.class);

    @Autowired
    private ProductInfoRepository  productInfoRepository;


    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findByProductType() {

        return productInfoRepository.findByProductType(ProductType.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findByAll(Pageable pageable) {

        return productInfoRepository.findAll(pageable);
    }

    @Override
    public void savaProductInfo(ProductInfo vo) {
        productInfoRepository.save(vo);

    }

    @Override
    public List<ProductInfo> findBycategoryType(Integer categoryType) {
        return null;
    }

    @Override
    public void updateProductInfo(ProductInfo vo) {

        productInfoRepository.save(vo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO:cartDTOList) {
            ProductInfo productInfo=productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo==null){

                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer number=cartDTO.getProductQuantity()+productInfo.getProductStock();
            productInfo.setProductStock(number);
            productInfoRepository.save(productInfo);

        }

    }

    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO:cartDTOList) {

            ProductInfo productInfo=productInfoRepository.findOne(cartDTO.getProductId());
             //判断商品是否存在
            if (productInfo==null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer number=productInfo.getProductStock()-cartDTO.getProductQuantity();
            //判断库存是否存在
            if (number==0){
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(number);
            productInfoRepository.save(productInfo);
        }
    }
}
