package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductType(Integer s);
}
