package com.imooc.sellv1.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ProductInfo  implements Serializable {


    @Id
    private String  productId;

    private String  productName;//商品名称

    private BigDecimal productPrice; //单价

    private Integer    productStock;  //库存

    private String     productDescription; //描述

    private String     productIcon; //小图

    private Integer    categoryType; //类目编号

    private Date       createTime; //创建时间

    private Date       updateTime; //修改时间

    private Integer    productType;//上架

}