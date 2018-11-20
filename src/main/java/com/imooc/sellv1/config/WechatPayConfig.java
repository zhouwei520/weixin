package com.imooc.sellv1.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 支付配置
 *
 * @author mac
 * @create 2018-11-19 9:38 PM
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig  wechatAccountConfig;


    @Bean
    public BestPayServiceImpl bestPayService(){



         BestPayServiceImpl  bestPayService=new BestPayServiceImpl();
          bestPayService.setWxPayH5Config(wxPayH5Config());
          return  bestPayService;
    }

    @Bean
    public  WxPayH5Config  wxPayH5Config(){
        WxPayH5Config  wxPayH5Config=new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());

        return wxPayH5Config;
    }


}