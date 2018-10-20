package com.imooc.sellv1.repository;

import com.imooc.sellv1.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


    //根据类目状态进行查询
     List<ProductCategory> findByCategoryTypeIn(List<Integer> listCategoryType);


}
