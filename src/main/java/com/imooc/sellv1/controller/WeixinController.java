package com.imooc.sellv1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 * weixin
 *
 * @author mac
 * @create 2018-11-11 10:04 PM
 */
@Slf4j
@Controller
@RequestMapping("/weixin")
public class WeixinController {

     @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
         log.info("进入auth方法");
         log.info(code);
         //公共号code =code
         //公众号的appsecret wxbee2a7f6374a9eab
         //appid 公众号的唯一标识 78c200afbc5fe986fec0b129f7b41033
         String appSecret="78c200afbc5fe986fec0b129f7b41033";
         String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbee2a7f6374a9eab&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
         RestTemplate restTemplate =new RestTemplate();
         String response=restTemplate.getForObject(url,String.class);
         log.info(response);
     }
}