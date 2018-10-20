package com.imooc.sellv1.controller;

import com.imooc.sellv1.dataobject.ProductCategory;
import com.imooc.sellv1.dataobject.ProductInfo;
import com.imooc.sellv1.service.ProductCategoryService;
import com.imooc.sellv1.service.ProductInfoService;
import com.imooc.sellv1.vo.ProductInfoVO;
import com.imooc.sellv1.vo.ProductVO;
import com.imooc.sellv1.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//
//@RestController
@Controller
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private Logger logger=LoggerFactory.getLogger(BuyerProductController.class);

    @Autowired
    private ProductCategoryService  productCategoryService;

    @Autowired
    private ProductInfoService   productInfoService;

    @GetMapping("/list")
    @ResponseBody
    public  ResultVO List(){
        //查询所有的上架商品
        List<ProductInfo> productInfoList=productInfoService.findByProductType();
//        //查询类目 一次性查询
//        List<Integer> categoryTypeList=new ArrayList<>();
//        //传统方法
//        for (ProductInfo product:productInfoList) {
//            categoryTypeList.add(product.getCategoryType());
//        }
        //jdk1.8方法
        List<Integer> categoryTypeLists=productInfoList.stream().
                map(e->e.getCategoryType()).collect(Collectors.toList());

       List<ProductCategory> productCategories= productCategoryService.findByCategoryTypeIn(categoryTypeLists);
       //封装数据
       List<ProductVO> productVOS= new ArrayList<>();
        for (ProductCategory productCategory:productCategories) {
           ProductVO productVO=new ProductVO();
           productVO.setCategoryType(productCategory.getCategoryType());
           productVO.setCategoryName(productCategory.getCategoryName());


               List<ProductInfoVO> productInfoVo = new ArrayList<>();
               for (ProductInfo product : productInfoList) {
                   if (product.getCategoryType().equals(productCategory.getCategoryType())) {
                       ProductInfoVO vo = new ProductInfoVO();
                       BeanUtils.copyProperties(product, vo);
                       productInfoVo.add(vo);
                   }
               }
               productVO.setCategoryFoods(productInfoVo);
               productVOS.add(productVO);

        }
        ResultVO obj=new ResultVO<>();
        obj.setCode(0);
        obj.setMsg("成功");
        obj.setData(productVOS);

        return  obj;
    }
}
