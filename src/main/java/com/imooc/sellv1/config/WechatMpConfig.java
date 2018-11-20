package com.imooc.sellv1.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 微信配置
 *
 * @author mac
 * @create 2018-11-14 8:01 PM
 */
@Component
public class WechatMpConfig {


    @Autowired
    private WechatAccountConfig wecharAccountConfig;


     @Bean
    public WxMpService wxMpService(){
         WxMpService wxMpService =new WxMpServiceImpl();
         wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
         return  wxMpService;
     }

     //微信的配置文件
     @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
         WxMpInMemoryConfigStorage  wxMpConfigStorage=new WxMpInMemoryConfigStorage();
         //设置appid 和公告号appSecret
         wxMpConfigStorage.setAppId(wecharAccountConfig.getMpAppId());
         wxMpConfigStorage.setSecret(wecharAccountConfig.getMpAppSecret());
         return  wxMpConfigStorage;
     }
}