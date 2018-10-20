package com.imooc.sellv1.service;

import com.imooc.sellv1.dataobject.ProductInfo;
import com.imooc.sellv1.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService  {

     //根据id进行查询
     ProductInfo findOne(String id);

     //根据商品的状态进行查询
     List<ProductInfo>  findByProductType();

     //查询所有的商品信息
     Page<ProductInfo> findByAll(Pageable pageable);

     //新增商品信息
     void  savaProductInfo(ProductInfo vo);

     //根据类目进行查询
     List<ProductInfo>  findBycategoryType(Integer categoryType);

     //下架某个商品
     void updateProductInfo(ProductInfo vo);

     //加库存
     void  increaseStock(List<CartDTO> cartDTOList);

    //减少库存
     void  decreaseStock(List<CartDTO> cartDTOList);
}
