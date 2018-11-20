package com.imooc.sellv1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 构建配置文件
 *
 * @author mac
 * @create 2018-11-14 8:11 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

   //公众号 appid
   private   String  mpAppId;

   //公众号AppSecret
   private   String  mpAppSecret;

    //商户号
   private String mchId;

   //商户密钥
   private String mchKey;

   //商户证书路径

   private String keyPath;

   //异步通知
   private String notifyUrl;
}