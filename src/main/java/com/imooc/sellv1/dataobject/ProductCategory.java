package com.imooc.sellv1.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;  //类目名字

    private Integer categoryType;  //类目编号

    private Date   createTime;  //创建时间

    private Date   updateTime;  //修改时间

}
