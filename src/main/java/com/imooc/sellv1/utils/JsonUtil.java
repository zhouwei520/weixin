package com.imooc.sellv1.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 描述:
 * json
 *
 * @author mac
 * @create 2018-11-20 8:27 PM
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder go=new GsonBuilder();
        go.setPrettyPrinting();
        Gson gson=go.create();
        return  gson.toJson(object);
     }
}