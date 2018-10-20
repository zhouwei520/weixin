package com.imooc.sellv1.utils;

import java.util.Random;

/**
 * 描述:
 * 生成组件
 *
 * @author mac
 * @create 2018-10-10 下午9:27
 */
public class KeyUtil {


    public  static  synchronized String  genUniqueKey(){
        Random random=new Random();

        Integer number=random.nextInt(900000)+10000;

        return System.currentTimeMillis()+String.valueOf(number);
    }

}