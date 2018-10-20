package com.imooc.sellv1.dataobject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Slf4j
public class OrderDetail  implements Serializable {

   @Id
   private  String  detailId;

   private  String  orderId;//订单 id

   private  String  productId;//商品id

   private  String  productName; //商品名字

   private  BigDecimal productPrice ;//商品价格

   private  Integer  productQuantity ; // 商品数量

   private  String   productIcon; // 图片

   private  Date     createTime; //创建时间

   private  Date     updateTime;// 修改时间
}
